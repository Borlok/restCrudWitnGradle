package com.borlok.crudrest.dto;

import com.borlok.crudrest.model.File;
import com.borlok.crudrest.model.FileStatus;
import com.borlok.crudrest.model.User;

public class FileDto {
    private int id;
    private int user_id;
    private String fileStatus;

    public FileDto() {
    }

    public File toFile() {
        File file = new File();
        file.setId(id);
        file.setFileStatus(FileStatus.valueOf(fileStatus));
        return file;
    }

    public static FileDto fromFile(File file) {
        FileDto fileDto = new FileDto();
        fileDto.id = file.getId();
        if (file.getUser() == null)
            file.setUser(new User());
        fileDto.user_id = file.getUser().getId();
        fileDto.fileStatus = file.getFileStatus().name();
        return fileDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", fileStatus='" + fileStatus + '\'' +
                '}';
    }
}
