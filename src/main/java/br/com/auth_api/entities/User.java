package br.com.auth_api.entities;

import br.com.auth_api.controllers.dto.LoginRequest;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serial;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_users")
@SequenceGenerator(name = "seq_user", sequenceName = "seq_user", initialValue = 1, allocationSize = 1)
public class User implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users")
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @CreationTimestamp
    private Instant createTimestamp;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_users_roles"
            ,uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"}, name = "unique_user_role"),

            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", table = "tb_users",
            unique = false, foreignKey = @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT)),

            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "tb_roles",
            unique = false, foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT))
    )
    private List<Role> roles;

    public User() {
    }

    public User(User user) {
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUserId(Long userId) {
        this.id = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateTimestamp(Instant createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}


