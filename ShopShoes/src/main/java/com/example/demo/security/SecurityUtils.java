//package com.example.demo.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import com.example.demo.dto.MyUser;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SecurityUtils {
//
//    public static MyUser getPrincipal() {
//        return (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
//    }
//}
