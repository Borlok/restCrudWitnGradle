package com.borlok.crudrest.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permit.AUTHORITY_READ)),
    MODERATOR(Set.of(Permit.AUTHORITY_READ, Permit.AUTHORITY_WRITE)),
    ADMIN(Set.of(Permit.AUTHORITY_ALL,Permit.AUTHORITY_READ,Permit.AUTHORITY_WRITE));

    private Set<Permit> permits;

    Role(Set<Permit> permits) {
        this.permits = permits;
    }

    public Set<Permit> getPermits() {
        return permits;
    }

    public Set<SimpleGrantedAuthority> getAuthorities () {
        return getPermits().stream()
                .map(x -> new SimpleGrantedAuthority(x.getPermit()))
                .collect(Collectors.toSet());
    }
}
