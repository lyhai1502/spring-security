package com.example.controller;

import com.example.dto.JwtResponseDTO;
import com.example.dto.LoginDTO;
import com.example.service.DemoService;
import com.example.service.JwtService;
import com.example.service.LoginService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private LoginService loginService;
    @Autowired
    private DemoService service;

    @PostMapping("api/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(loginService.loginUser(loginDTO));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/user")
    public ResponseEntity<?> demoUser()
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(service.checkPermission(authentication));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/admin")
    public ResponseEntity<?> demoAdmin()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(service.checkPermission(authentication));
    }

//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("api/create-user")
    public ResponseEntity<?> createUser(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(loginService.createUser(loginDTO));
    }
}