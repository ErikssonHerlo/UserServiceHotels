package com.erikssonherlo.user.infrastructure.adapter.output.persistence.entity;

import com.erikssonherlo.user.domain.model.enums.Role;
import com.erikssonherlo.user.domain.model.enums.Position;
import com.erikssonherlo.user.domain.model.enums.Establishment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "nit", unique = true)
    private String nit;  // Número de Identificación Tributaria

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;  // Rol: CLIENT, EMPLOYEE, ADMINISTRATOR

    @Column(name = "password", nullable = false)
    private String password;  // Se mapea solo en la entidad

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;  // Solo para empleados

    @Column(name = "weekly_salary")
    private Double weeklySalary;  // Salario para empleados

    @Column(name = "hire_date")
    private LocalDate hireDate;  // Fecha de contratación para empleados

    @Column(name = "establishment_type")
    @Enumerated(EnumType.STRING)
    private Establishment establishmentType;  // HOTEL o RESTAURANT (para empleados)

    @Column(name = "establishment_id")
    private Long establishmentId;  // ID del establecimiento asociado al empleado

    @Column(name = "is_preferred")
    private Boolean isPreferred;  // Solo para clientes

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    // Implementing UserDetails methods for Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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

    @Override
    public String getUsername() {
        return this.email;
    }
}
