package com.team.rambla.websitedbspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ERoles name;

    public Role(ERoles name) {
        this.name = name;
    }

    // TODO! Change later
    public Role() {
        this.name = ERoles.ROLE_ADMIN;
    }
}
