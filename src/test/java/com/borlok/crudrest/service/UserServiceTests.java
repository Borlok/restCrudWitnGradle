package com.borlok.crudrest.service;

import com.borlok.crudrest.model.User;
import com.borlok.crudrest.repository.UserRepository;
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
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserService userService;
    @Mock
    private User user;
    @Mock
    private List<User> users;

    @Before
    public void setUp() {
        int id = 1;
        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.getOne(id)).thenReturn(user);
        userService = new UserService(userRepository);
    }

    @Test
    public void notNullTest() {
        assertNotNull(userRepository);
        assertNotNull(userService);
        assertNotNull(user);
        assertNotNull(users);
    }

    @Test
    public void getByIdTest () {
        assertNotNull(userService.getById(1));
        assertEquals(user, userService.getById(1));
    }

    @Test
    public void getAllTest() {
        assertNotNull(userService.getAll());
        assertEquals(users, userService.getAll());
    }

    @Test
    public void createTest() {
        assertNotNull(userService.create(user));
        assertEquals(user, userService.create(user));
    }
}
