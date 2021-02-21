package com.borlok.crudrest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private UserDetailsService userDetailsService;
    @Value("${jwt.header}")
    private String authenticationHeader;
    @Value("${jwt.validation}")
    private Long validityInMilliseconds;
    @Value("${jwt.secret}")
    private String secret;
    private Logger log = LogManager.getLogger(this);

    @Autowired
    public JwtTokenProvider(@Qualifier("UserDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username, String role) {
        log.info("Получили из реквеста юзера и роль: " + username + " " + role);
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        Date now = new Date();
        Date validate = new Date(now.getTime() + validityInMilliseconds * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public boolean validationToken(String token) {
        try {
            return  !Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
        } catch (JwtTokenException | IllegalArgumentException e) {
            throw new JwtTokenException("invalid token", HttpServletResponse.SC_FORBIDDEN);
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(authenticationHeader);
    }
}
