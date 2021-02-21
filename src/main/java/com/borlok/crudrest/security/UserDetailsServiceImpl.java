package com.borlok.crudrest.security;

import com.borlok.crudrest.model.Access;
import com.borlok.crudrest.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccessRepository accessRepository;

    @Autowired
    public UserDetailsServiceImpl(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Access user = accessRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User was not be found"));
        return UserDetailsImpl.fromAccess(user);
    }
}
