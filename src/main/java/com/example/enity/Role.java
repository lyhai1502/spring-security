package com.example.enity;


import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role", uniqueConstraints = {
                @UniqueConstraint(name = "ROLE_UK", columnNames = "role_name") })
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;
}