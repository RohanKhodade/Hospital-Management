package com.example.hospitalManagement.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public String getLoggedUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    public void checkAccess(String username){
        String loggedUsername=getLoggedUsername();
        if (!loggedUsername.equals(username)){
           throw new AccessDeniedException("you have access to only your records");
        }
    }
}
