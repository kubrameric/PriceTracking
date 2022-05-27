package com.bitirme.login.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof MyUserDetails) {
            return  ((MyUserDetails)principal).getUsername();
        } else {
            return  principal.toString();
        }

    }
}
