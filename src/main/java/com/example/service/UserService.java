package com.example.service;

import com.example.dto.JwtResponseDTO;
import com.example.dto.RequestAuthenticationDTO;
import com.example.enity.User;

public interface UserService {
    JwtResponseDTO loginUser(RequestAuthenticationDTO requestAuthenticationDTO);
    User createUser(RequestAuthenticationDTO requestAuthenticationDTO);
}
