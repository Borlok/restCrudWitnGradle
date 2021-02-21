package com.borlok.crudrest.security;

import com.borlok.crudrest.model.Access;
import com.borlok.crudrest.model.AccessStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public UserDetailsImpl(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    public static UserDetails fromAccess(Access access) {
        return new User(
                access.getEmail(),
                access.getPassword(),
                access.getStatus().equals(AccessStatus.ACTIVE),
                access.getStatus().equals(AccessStatus.ACTIVE),
                access.getStatus().equals(AccessStatus.ACTIVE),
                access.getStatus().equals(AccessStatus.ACTIVE),
                access.getRole().getAuthorities()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public String toString() {
        return "UserDetailsImpl{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", isActive=" + isActive +
                '}';
    }
}
