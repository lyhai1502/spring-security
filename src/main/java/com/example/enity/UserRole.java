package com.example.enity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "User_Role", uniqueConstraints = {
                @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "User_Id", "Role_Id" }) })
public class UserRole {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "Role_Id", nullable = false)
    private AppRole appRole;

    @Override
    public String toString() {
        return appRole.getRoleName();
    }
}