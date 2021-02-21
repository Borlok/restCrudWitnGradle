package com.borlok.crudrest.service;

import com.borlok.crudrest.model.Account;
import com.borlok.crudrest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountService() {
    }

    public Account create(Account account) {
        Account returnedAccount = accountRepository.save(account);
        accountRepository.flush();
        return returnedAccount;
    }

    public Account getById(Integer id) {
        return accountRepository.getOne(id);
    }

    public List<Account> getAll () {
        return accountRepository.findAll();
    }

    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }
}
