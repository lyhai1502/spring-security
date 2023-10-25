package com.example.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponseDTO implements Serializable {
    private String token;
    private String type = "Bearer";
    private String username;
    private String role;
    public JwtResponseDTO(String accessToken, String username, String roles) {
        this.token = accessToken;
        this.username = username;
        this.role = roles;
    }
}
