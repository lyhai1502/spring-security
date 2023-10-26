package com.example.enity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue
    @Column(name = "token_id", nullable = false)
    private long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expired", nullable = false)
    private boolean expired;

    @Column(name = "date_expired")
    private Date date_expired;

    @Column(name = "revoked", nullable = false)
    private boolean revoked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
