package com.example.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public String checkPermission(Authentication authentication)
    {
        return authentication.getAuthorities().toString();
    }
}
