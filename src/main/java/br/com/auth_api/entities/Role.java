package br.com.auth_api.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    private String name;

    public Long getId() {
        return roleId;
    }

    public void setId(Long id) {
        this.roleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
