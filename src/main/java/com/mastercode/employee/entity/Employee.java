package com.mastercode.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Positive(message = "Id must be positive value")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String surname;
    @Column(nullable = false)
    private BigDecimal salary;

}