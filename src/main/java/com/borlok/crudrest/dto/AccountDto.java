package com.borlok.crudrest.dto;

import com.borlok.crudrest.model.Account;
import com.borlok.crudrest.model.AccountStatus;
import com.borlok.crudrest.model.User;

public class AccountDto {
    private int id;
    private int user_id;
    private String name;
    private String status;

    public AccountDto() {
    }

    public Account toAccount (){
        Account account = new Account();
        account.setId(id);
        if (!name.equals(""))
            account.setName(name);
        account.setStatus(AccountStatus.valueOf(status));
        return account;
    }

    public static AccountDto fromAccount(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.id = account.getId();
        if (account.getUser() == null)
            account.setUser(new User());
        accountDto.user_id = account.getUser().getId();
        accountDto.name = account.getName();
        accountDto.status = account.getStatus().name();
        return accountDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
