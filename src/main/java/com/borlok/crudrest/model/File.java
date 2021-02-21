package com.borlok.crudrest.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "file_status")
    private FileStatus fileStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public File() {
        id = 0;
        fileStatus = FileStatus.DELETED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FileStatus getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fileStatus=" + fileStatus +
                '}';
    }
}
