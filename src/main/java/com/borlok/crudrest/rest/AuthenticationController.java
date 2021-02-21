package com.borlok.crudrest.rest;

import com.borlok.crudrest.dto.AuthenticationRequestDto;
import com.borlok.crudrest.model.Access;
import com.borlok.crudrest.repository.AccessRepository;
import com.borlok.crudrest.security.JwtTokenProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private AccessRepository accessRepository;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    private Logger log = LogManager.getLogger(this);

    @Autowired
    public AuthenticationController(AccessRepository accessRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.accessRepository = accessRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            log.info("request to api/v1/auth/login");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(),requestDto.getPassword()));
            Access user = accessRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", requestDto.getEmail());
            response.put("token", token);
            log.info("Token was create to email: " + requestDto.getEmail());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("invalid authentication", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        log.info("Security logout was complete");
    }
}
