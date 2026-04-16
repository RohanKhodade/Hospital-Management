package com.example.hospitalManagement.service.implementations;

import com.example.hospitalManagement.dto.UserDto;
import com.example.hospitalManagement.entity.Role;
import com.example.hospitalManagement.entity.User;
import com.example.hospitalManagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GeneralUserServices {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public GeneralUserServices(PasswordEncoder passwordEncoder,
                               UserRepository userRepository){
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
    }
    public String createUser(UserDto dto, Role role){
        User user=new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);
        userRepository.save(user);
        return "ADMIN saved successfully";
    }
}
