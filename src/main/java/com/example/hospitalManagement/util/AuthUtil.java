package com.example.hospitalManagement.util;

import com.example.hospitalManagement.entity.Role;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public String getLoggedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    public UserDetails getLoggedUser(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().
                getPrincipal();
    }
    public void checkAccess(String username) {
        String loggedUsername = getLoggedUsername();
        if (!loggedUsername.equals(username)) {
            throw new AccessDeniedException("you have access to only your records");
        }
    }
}