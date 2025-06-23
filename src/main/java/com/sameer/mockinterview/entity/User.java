package com.sameer.mockinterview.entity;

import com.sameer.mockinterview.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique= true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean enabled = true;
}
