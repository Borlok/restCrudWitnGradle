package com.borlok.crudrest.service;

import com.borlok.crudrest.model.Account;
import com.borlok.crudrest.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTests {
    @Mock
    private AccountRepository accountRepository;
    @Spy
    private AccountService accountService;
    @Mock
    private Account account;
    @Mock
    private List<Account> accounts;

    @Before
    public void setUp() {
        int id = 1;
        when(accountRepository.findAll()).thenReturn(accounts);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountRepository.getOne(id)).thenReturn(account);
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void notNullTest() {
        assertNotNull(accountRepository);
        assertNotNull(accountService);
        assertNotNull(account);
        assertNotNull(accounts);
    }

    @Test
    public void getByIdTest () {
        assertNotNull(accountService.getById(1));
        assertEquals(account, accountService.getById(1));
    }

    @Test
    public void getAllTest() {
        assertNotNull(accountService.getAll());
        assertEquals(accounts, accountService.getAll());
    }

    @Test
    public void createTest() {
        assertNotNull(accountService.create(account));
        assertEquals(account, accountService.create(account));
    }
}
