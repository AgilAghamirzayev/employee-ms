package com.mastercode.employee.services;

import com.mastercode.employee.dto.EmployeeDTO;
import com.mastercode.employee.entity.Employee;
import com.mastercode.employee.mapper.EmployeeMapper;
import com.mastercode.employee.repositoty.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {

    private static final EmployeeMapper MAPPER = EmployeeMapper.INSTANCE;

    @MockBean
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    Employee employee = new Employee(1L, "Aqil", "Zeka", BigDecimal.valueOf(111));

    @Test
    void getAllEmployees() {
        List<Employee> employees = Arrays.asList(employee,
                new Employee(2L, "Ali", "Aliyev", BigDecimal.valueOf(1111)));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();

        assertEquals(2, allEmployees.size());
    }

    @Test
    void getEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeDTO returnedEmployee = employeeService.getEmployeeById(1L);
        Employee expected = MAPPER.employeeDtoToEmployee(returnedEmployee);

        assertEquals(expected, employee);
    }

    @Test
    void createEmployee() {
        when(employeeRepository.save(any())).thenReturn(employee);

        EmployeeDTO employeeDTO = MAPPER.employeeToEmployeeDTO(employee);
        EmployeeDTO createdValue = employeeService.createEmployee(employeeDTO);
        Employee expected = MAPPER.employeeDtoToEmployee(createdValue);

        assertEquals(expected, employee);
    }

    @Test
    void deleteEmployeeById() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        employeeService.deleteEmployeeById(employee.getId());
        verify(employeeRepository).deleteById(employee.getId());
    }
}