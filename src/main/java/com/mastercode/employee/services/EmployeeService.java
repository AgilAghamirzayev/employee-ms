package com.mastercode.employee.services;

import com.mastercode.employee.dto.EmployeeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO createEmployee(EmployeeDTO employeeDetails);
    void updateEmployee(Long id, EmployeeDTO employeeDetails);
    void deleteEmployeeById(Long id);
    Page<EmployeeDTO> getAllEmployeesPage(Integer pageNum,Integer pageSize,
                                          String sortBy, String sortDir, String filter);
}
