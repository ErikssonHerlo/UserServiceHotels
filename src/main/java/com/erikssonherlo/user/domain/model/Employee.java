package com.erikssonherlo.user.domain.model;

import com.erikssonherlo.user.domain.model.enums.Establishment;
import com.erikssonherlo.user.domain.model.enums.Position;
import com.erikssonherlo.user.domain.model.enums.Role;
import com.erikssonherlo.user.infrastructure.adapter.output.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends User {
    private Position position;
    private double weeklySalary;
    private LocalDate hireDate;
    private Establishment establishmentType;
    private Long establishmentId;

    /**
     * PRINCIPAL CONSTRUCTOR
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param address
     * @param nit
     * @param position
     * @param weeklySalary
     * @param hireDate
     * @param establishmentType
     * @param establishmentId
     * @param createdAt
     * @param updatedAt
     * @param deletedAt
     */
    public Employee(Long id, String firstName, String lastName, String email, String phone, String address, String nit, Position position, double weeklySalary, LocalDate hireDate, Establishment establishmentType, Long establishmentId, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(id, firstName, lastName, email, phone, address, nit, Role.EMPLOYEE, createdAt, updatedAt, deletedAt);
        this.position = position;
        this.weeklySalary = weeklySalary;
        this.hireDate = hireDate;
        this.establishmentType = establishmentType;
        this.establishmentId = establishmentId;
    }

    /**
     * CONSTRUCTOR USED TO CONVERT FROM USER ENTITY TO EMPLOYEE
     * @param userEntity
     */
    public Employee(UserEntity userEntity) {
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
        this.position = userEntity.getPosition();
        this.weeklySalary = userEntity.getWeeklySalary();
        this.hireDate = userEntity.getHireDate();
        this.establishmentType = userEntity.getEstablishmentType();
        this.establishmentId = userEntity.getEstablishmentId();
    }
}
