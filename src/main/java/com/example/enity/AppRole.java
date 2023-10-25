package com.example.enity;


import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "App_Role", uniqueConstraints = {
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name") })
public class AppRole {

    @Id
    @GeneratedValue
    @Column(name = "Role_Id", nullable = false)
    private Long roleId;

    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;
}