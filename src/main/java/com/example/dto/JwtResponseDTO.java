package com.example.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponseDTO implements Serializable {
    private String type = "Bearer";
    private String token;
    private String userName;
    private String roleName;
    public JwtResponseDTO(String accessToken, String userName, String roleName) {
        this.token = accessToken;
        this.userName = userName;
        this.roleName = roleName;
    }
}
