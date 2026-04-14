package com.example.hospitalManagement.controller;
import com.example.hospitalManagement.config.SecurityConfig;
import com.example.hospitalManagement.dto.UserDto;
import com.example.hospitalManagement.service.implementations.UserServiceImpl;
import com.example.hospitalManagement.util.JwtUtility;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    public AuthController(JwtUtility jwtUtility,
                          AuthenticationManager authenticationManager){
        this.jwtUtility=jwtUtility;
        this.authenticationManager=authenticationManager;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(),
                            userDto.getPassword())
            );
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jwtUtility.generateToken(userDto.getUsername()),
                HttpStatus.OK);
    }
}