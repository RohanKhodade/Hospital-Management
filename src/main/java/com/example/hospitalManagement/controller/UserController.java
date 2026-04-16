package com.example.hospitalManagement.controller;

import com.example.hospitalManagement.dto.UserDto;
import com.example.hospitalManagement.entity.Role;
import com.example.hospitalManagement.service.implementations.GeneralUserServices;
import com.example.hospitalManagement.service.implementations.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final GeneralUserServices userServices;
    public UserController(UserServiceImpl userServiceImpl,
                          GeneralUserServices userServices){
        this.userServiceImpl=userServiceImpl;
        this.userServices=userServices;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/admin")
    public ResponseEntity<String> createAdmin(@RequestBody UserDto dto){
        return new ResponseEntity<>(userServices.createUser(dto,Role.ROLE_ADMIN),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    @PostMapping("create/receptionist")
    public ResponseEntity<String> createReceptionist(@RequestBody UserDto dto){
        return new ResponseEntity<>(userServices.createUser(dto, Role.ROLE_RECEPTIONIST),
                HttpStatus.OK);
    }
}