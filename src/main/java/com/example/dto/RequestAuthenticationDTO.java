package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAuthenticationDTO {
    private String email;
    private String password;
    private String roleName = null;
}
