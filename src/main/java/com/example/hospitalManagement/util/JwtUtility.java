package com.example.hospitalManagement.util;

import com.example.hospitalManagement.service.implementations.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
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

    private final String SECRET="secret key to generate jwt token";
    private final SecretKey KEY=Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long EXPIRATION_TIME=1000*60*60;

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    public String extractUsername(String token){
        Claims body=Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)// verifies tokens expiration time, is valid
                .getPayload();
        return body.getSubject();
    }
}

