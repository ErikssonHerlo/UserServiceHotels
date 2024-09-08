-- USERS MICRO-SERVICE DATABASE

-- Tabla para almacenar información de usuarios (clientes, empleados, administradores)
CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(255) NOT NULL,
                      last_name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) UNIQUE NOT NULL, -- Email del usuario, debe ser único
                      phone VARCHAR(20),
                      address VARCHAR(255),
                      nit VARCHAR(15) UNIQUE, -- NIT del usuario, también debe ser único
                      role ENUM('CLIENT', 'EMPLOYEE', 'ADMINISTRATOR') NOT NULL, -- Rol del usuario
                      password VARCHAR(255) NOT NULL,
                      is_preferred BOOLEAN DEFAULT FALSE, -- Indica si el cliente es preferido
                      position VARCHAR(255),
                      weekly_salary DECIMAL(10, 2),
                      hire_date DATE,
                      establishment_type ENUM('HOTEL', 'RESTAURANT'), -- Tipo de establecimiento si es empleado
                      establishment_id BIGINT, -- ID del establecimiento asociado
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      deleted_at TIMESTAMP NULL DEFAULT NULL, -- Soft delete
                      CHECK (
                          (
                              role = 'EMPLOYEE'
                                  AND position IS NOT NULL
                                  AND weekly_salary IS NOT NULL
                                  AND hire_date IS NOT NULL
                              ) OR role = 'CLIENT'
                          )
);

-- Índices para búsqueda eficiente
CREATE INDEX idx_user_email ON user (email);
CREATE INDEX idx_user_nit ON user (nit);