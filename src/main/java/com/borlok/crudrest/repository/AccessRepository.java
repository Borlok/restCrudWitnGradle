package com.borlok.crudrest.repository;

import com.borlok.crudrest.model.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<Access, Integer> {
    Optional<Access> findByEmail(String email);
}
