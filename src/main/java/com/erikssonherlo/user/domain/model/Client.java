package com.erikssonherlo.user.domain.model;


import com.erikssonherlo.user.domain.model.enums.Role;
import com.erikssonherlo.user.infrastructure.adapter.output.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {
    private boolean isPreferred;

    // Principal constructor
    public Client(Long id, String firstName, String lastName, String email, String phone, String address, String nit, boolean isPreferred, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(id, firstName, lastName, email, phone, address, nit, Role.CLIENT, createdAt, updatedAt, deletedAt);
        this.isPreferred = isPreferred;
    }

    // Constructor used to convert from UserEntity to Client
    public Client(UserEntity userEntity) {
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
        this.isPreferred = userEntity.getIsPreferred();
    }
}

