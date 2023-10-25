package com.example.service;

import com.example.dto.JwtResponseDTO;
import com.example.dto.LoginDTO;
import com.example.enity.AppUser;

public interface LoginService {
    public JwtResponseDTO loginUser(LoginDTO loginDTO);
    public AppUser createUser(LoginDTO loginDTO);
}
