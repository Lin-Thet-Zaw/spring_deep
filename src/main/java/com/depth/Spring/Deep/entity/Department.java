package com.depth.Spring.Deep.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @NotBlank(message = "Department name is mandatory")
    private String departmentName;

    @NotBlank(message = "Department address is required")
    private String departmentAddress;

    @NotBlank(message = "Department code cannot be empty")
    private String departmentCode;
}