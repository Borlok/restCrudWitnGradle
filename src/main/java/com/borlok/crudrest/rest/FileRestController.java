package com.borlok.crudrest.rest;

import com.borlok.crudrest.dto.FileDto;
import com.borlok.crudrest.model.File;
import com.borlok.crudrest.model.FileStatus;
import com.borlok.crudrest.model.User;
import com.borlok.crudrest.rest.util.S3Utils;
import com.borlok.crudrest.security.JwtTokenProvider;
import com.borlok.crudrest.service.FileService;
import com.borlok.crudrest.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/files")
public class FileRestController {
    private FileService fileService;
    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;
    private Logger log = LogManager.getLogger(this);

    @Autowired
    public FileRestController(FileService fileService, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.fileService = fileService;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('access:moderator')")
    public FileDto create(@RequestBody MultipartFile multipartFile,
                          @RequestHeader("Authorization") String token) throws IOException { //token нужен для создания бакета
        if (multipartFile != null) {
            log.info("Creating file");
            File file = new File();
            User user = userService.getByEmail(jwtTokenProvider.getUsername(token));
            try {
                user.getFiles().add(file);
                file.setUser(user);
                file.setFileStatus(FileStatus.ACTIVE);
                file.setName(multipartFile.getOriginalFilename());
                file = fileService.create(file);

                S3Utils.addFile(multipartFile, file);

                log.info("Create file");

                return FileDto.fromFile(file);
            } catch (S3Exception | IllegalArgumentException e) {
                log.error(e.getMessage());
                fileService.deleteById(file.getId());
            }
        }
        return null;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('access:user')")
    public FileDto getById(@PathVariable("id") Integer id) {
        log.info("Get file with id: " + id);
        File file = fileService.getById(id);
        S3Utils.getFile(file);
        return FileDto.fromFile(file);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('access:user')")
    public List<FileDto> getAll() {
        log.info("Get all files");
        return fileService.getAll().stream().map(FileDto::fromFile).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('access:moderator')")
    public void deleteById(@PathVariable("id") Integer id) {
        log.info("Delete file with id: " + id);
        S3Utils.cleanUp(fileService.getById(id));
        fileService.deleteById(id);
    }
}
