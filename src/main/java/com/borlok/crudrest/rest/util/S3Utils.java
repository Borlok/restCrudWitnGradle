package com.borlok.crudrest.rest.util;

import com.borlok.crudrest.model.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;

public class S3Utils {
    private static Logger log = LogManager.getRootLogger();
    private static String downloadPath = "./downloads/";
    private static String bucket = "borlokbucket";

    private static S3Client connect() {
        Region region = Region.EU_NORTH_1;
        return S3Client.builder()
                .region(region).build();
    }

    public static void addFile(MultipartFile multipartFile, File savedFile) throws IOException, IllegalArgumentException {
        Region region = Region.EU_NORTH_1;
        S3Client s3 = connect();
        String key = BucketUtils.getKeyFromFile(savedFile);

        createBucket(s3, bucket, region);

        log.info("Uploading object...");
        s3.putObject(PutObjectRequest.builder().bucket(bucket).key(key)
                        .build(),
                RequestBody.fromBytes(multipartFile.getBytes()));
        log.info("Upload complete");
        log.info("Closing the connection to Amazon S3");
        s3.close();
        log.info("Connection closed");
        log.info("Exiting...");
    }

    private static void createBucket(S3Client s3Client, String bucketName, Region region) throws S3Exception {
        try {
            log.info("Creating bucket");
            s3Client.createBucket(CreateBucketRequest
                    .builder()
                    .bucket(bucketName)
                    .createBucketConfiguration(
                            CreateBucketConfiguration.builder()
                                    .locationConstraint(region.id())
                                    .build())
                    .build());
            log.info("Creating bucket: " + bucketName);
            s3Client.waiter().waitUntilBucketExists(HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
            log.info(bucketName + " is ready.");
        } catch (S3Exception e) {
            log.error(e.awsErrorDetails().errorMessage());
            log.info("Bucket is already exist");
        }
    }

    public static void getFile(File file) {
        String key = BucketUtils.getKeyFromFile(file);
        S3Client s3Client = connect();
        java.io.File path = new java.io.File(downloadPath);
        if (!path.exists()) {
            path.mkdir();
        }
        path = new java.io.File(downloadPath + file.getName());
        s3Client.getObject(GetObjectRequest.builder().bucket(bucket).key(key).build(), path.toPath());
        log.info("File was download");
        s3Client.close();
        log.info("Connection closed");
    }

    public static void cleanUp(File file) {
        String key = BucketUtils.getKeyFromFile(file);
        S3Client s3Client = connect();
        log.info("Begin cleaning process");
        try {
            log.info("Deleting object: " + key);
            s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucket).key(key).build());
            log.info(key + " has been deleted.");
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        log.info("Cleanup complete");
        s3Client.close();
        log.info("Connection closed");

    }
}
