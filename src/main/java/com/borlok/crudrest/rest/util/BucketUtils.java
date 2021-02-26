package com.borlok.crudrest.rest.util;

import com.borlok.crudrest.model.File;

public class BucketUtils {

    public static String getBucketNameFromFile (File file) {
        String[] parseEmail = file.getUser().getEmail().split("[@.]");
        return parseEmail[2] + "-" + parseEmail[1] + "-" + parseEmail[0] + "-bucket"
                + file.getUser().getEmail().hashCode();
    }

    public static String getKeyFromFile(File file) {
        return "" + file.getId() + "-" + file.getName();
    }
}
