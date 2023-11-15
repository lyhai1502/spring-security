package com.example.controller;

import com.example.dto.RequestAuthenticationDTO;
import com.example.service.DemoService;
import com.example.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DemoService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/user")
    public ResponseEntity<?> demoUser()
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(service.checkPermission(authentication));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin")
    public ResponseEntity<?> demoAdmin()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(service.checkPermission(authentication));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Admin')")
    @PostMapping("api/create-user")
    public ResponseEntity<?> createUser(@RequestBody RequestAuthenticationDTO requestAuthenticationDTO) {
        return ResponseEntity.ok(userService.createUser(requestAuthenticationDTO));
    }


    @PostMapping("api/login")
    public ResponseEntity<?> loginUser(@RequestBody RequestAuthenticationDTO requestAuthenticationDTO) {
        return ResponseEntity.ok(userService.loginUser(requestAuthenticationDTO));
    }
}