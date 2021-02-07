package com.khilkevichigor.shubaspringbootvue.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHelper {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode("admin");
        System.out.println(encodePassword);
    }
}
