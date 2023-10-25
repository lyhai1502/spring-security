package com.example.enity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Token")
public class Token {
    @Id
    @GeneratedValue
    @Column(name = "Token_Id", nullable = false)
    private long id;

    @Column(name = "Token", nullable = false)
    private String token;

    @Column(name = "Expired", nullable = false)
    private boolean expired;

    @Column(name = "Revoked", nullable = false)
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name="User_Id")
        private AppUser appUser;
}
