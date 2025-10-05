package com.Security.JwtAccessRefresh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    @Column
    private  String userId;

    private String username;
    private String password;

    @ManyToMany(fetch =FetchType.LAZY)
    @JoinTable(
            name = "users_names",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> roles = new HashSet<>();

}
