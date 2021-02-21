package com.borlok.crudrest.service;

import com.borlok.crudrest.model.File;
import com.borlok.crudrest.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileService() {
    }

    public File create(File file) {
        File returnedFile = fileRepository.save(file);
        fileRepository.flush();
        return returnedFile;
    }

    public File getById (int id) {
        return fileRepository.getOne(id);
    }

    public List<File> getAll() {
        return fileRepository.findAll();
    }

    public void deleteById (Integer id) {
        fileRepository.deleteById(id);
    }
}
