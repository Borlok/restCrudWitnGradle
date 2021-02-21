package com.borlok.crudrest.repository;

import com.borlok.crudrest.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
