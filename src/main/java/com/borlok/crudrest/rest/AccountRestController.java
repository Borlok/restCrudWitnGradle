package com.borlok.crudrest.rest;

import com.borlok.crudrest.dto.AccountDto;
import com.borlok.crudrest.model.Account;
import com.borlok.crudrest.model.User;
import com.borlok.crudrest.service.AccountService;
import com.borlok.crudrest.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountRestController {
    private AccountService accountService;
    private UserService userService;
    private Logger log = LogManager.getLogger(this);

    @Autowired
    public AccountRestController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('access:admin')")
    public AccountDto create(@RequestBody AccountDto accountDto) {
        log.info("Creating account with name: " + accountDto.getName());
        Account account = accountDto.toAccount();
        User user = new User();
        if (accountDto.getUser_id() != 0)
            user = userService.getById(accountDto.getUser_id());
        user.setAccount(account);
        account.setUser(user);
        log.info("Account with name: " + accountDto.getName() + " was making");
        return AccountDto.fromAccount(accountService.create(account));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('access:moderator')")
    public AccountDto getById(@PathVariable("id") Integer id) {
        log.info("Requesting account with id: " + id);
        return AccountDto.fromAccount(accountService.getById(id));

    }

    @GetMapping
    @PreAuthorize("hasAuthority('access:moderator')")
    public List<AccountDto> getAll () {
        log.info("Requesting all accounts");
        return accountService.getAll().stream().map(AccountDto::fromAccount).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('access:admin')")
    public void deleteById(@PathVariable("id") Integer id) {
        log.info("Deleting account with id: " + id);
        accountService.deleteById(id);
    }
}
