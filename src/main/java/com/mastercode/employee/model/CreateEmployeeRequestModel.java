package com.mastercode.employee.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequestModel {

    @NotNull(message = "First name cannot be null")
    @Size(min = 3, max = 50, message = "Name must be equal or greater than 3 characters and less than 50 characters")
    private String name;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 3, max = 50, message = "Surname must be equal or greater than 3 characters and less than 50 characters")
    private String surname;

    @NotNull(message = "Password cannot be null")
    @Positive(message = "Salary must be positive value")
    @DecimalMin(value = "0.0", message = "Salary must be equal or greater than 0.0")
    private BigDecimal salary;
}
