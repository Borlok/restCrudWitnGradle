package com.borlok.crudrest.service;

import com.borlok.crudrest.model.Account;
import com.borlok.crudrest.model.File;
import com.borlok.crudrest.repository.AccountRepository;
import com.borlok.crudrest.repository.FileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTests {
    @Mock
    private FileRepository fileRepository;
    @Spy
    private FileService fileService;
    @Mock
    private File file;
    @Mock
    private List<File> files;

    @Before
    public void setUp() {
        int id = 1;
        when(fileRepository.findAll()).thenReturn(files);
        when(fileRepository.save(file)).thenReturn(file);
        when(fileRepository.getOne(id)).thenReturn(file);
        fileService = new FileService(fileRepository);
    }

    @Test
    public void notNullTest() {
        assertNotNull(fileRepository);
        assertNotNull(fileService);
        assertNotNull(file);
        assertNotNull(files);
    }

    @Test
    public void getByIdTest () {
        assertNotNull(fileService.getById(1));
        assertEquals(file, fileService.getById(1));
    }

    @Test
    public void getAllTest() {
        assertNotNull(fileService.getAll());
        assertEquals(files, fileService.getAll());
    }

    @Test
    public void createTest() {
        assertNotNull(fileService.create(file));
        assertEquals(file, fileService.create(file));
    }
}
