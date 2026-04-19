package com.example.hospitalManagement.util;

import com.example.hospitalManagement.service.implementations.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtility {

    private final UserServiceImpl userServiceImpl;
    public JwtUtility(UserServiceImpl userServiceImpl){
        this.userServiceImpl=userServiceImpl;
    }

    @Value("${jwt.secret}")
    private String SECRET;
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getKey())
                .compact();
    }

    public String extractUsername(String token){
        Claims body=Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)// verifies tokens expiration time, is valid
                .getPayload();
        return body.getSubject();
    }
}

