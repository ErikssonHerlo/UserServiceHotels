package com.erikssonherlo.user.domain.model;

import com.erikssonherlo.user.infrastructure.adapter.output.persistence.entity.UserEntity;
import com.erikssonherlo.user.domain.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Administrator extends User {

    /**
     * Principal constructor
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param address
     * @param nit
     * @param createdAt
     * @param updatedAt
     * @param deletedAt
     */
    public Administrator(Long id, String firstName, String lastName, String email, String phone, String address, String nit, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(id, firstName, lastName, email, phone, address, nit, Role.ADMINISTRATOR, createdAt, updatedAt, deletedAt);
    }

    /**
     * Constructor used to convert from UserEntity to Administrator
     * @param userEntity
     */
    public Administrator(UserEntity userEntity) {
        super(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                userEntity.getAddress(),
                userEntity.getNit(),
                userEntity.getRole(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt(),
                userEntity.getDeletedAt()
        );
    }
}
