package com.mastercode.employee.services.impl;

import com.mastercode.employee.exception.EmployeeNotFoundException;
import com.mastercode.employee.repositoty.EmployeeRepository;
import com.mastercode.employee.dto.EmployeeDTO;
import com.mastercode.employee.entity.Employee;
import com.mastercode.employee.mapper.EmployeeMapper;
import com.mastercode.employee.mapper.PageMapping;
import com.mastercode.employee.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final EmployeeMapper MAPPER = EmployeeMapper.INSTANCE;

    private final EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.info("Getting all employees ...");
        Iterable<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> returnValue = MAPPER.employeeListToEmployeeDTOList(employees);

        return returnValue;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {

        log.info("Getting employee with id: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new EmployeeNotFoundException("Employee", id));

        EmployeeDTO returnValue = MAPPER.employeeToEmployeeDTO(employee);

        return returnValue;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDetails) {

        Employee employee = MAPPER.employeeDtoToEmployee(employeeDetails);
        Employee savedValue = employeeRepository.save(employee);
        EmployeeDTO returnValue = MAPPER.employeeToEmployeeDTO(savedValue);
        log.info("Saved employee: {}", returnValue);

        return returnValue;
    }

    @Override
    public void updateEmployee(Long id, EmployeeDTO employeeDTO) {

        log.info("Updating employee with id: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new EmployeeNotFoundException("Employee", id));

        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setSalary(employeeDTO.getSalary());

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {

        log.info("Deleting employee with id: {}", id);
        employeeRepository.findById(id).orElseThrow(()
                -> new EmployeeNotFoundException("Employee", id));

        employeeRepository.deleteById(id);

    }

    @Override
    public Page<EmployeeDTO> getAllEmployeesPage(Integer pageNum, Integer pageSize,
                                                 String sortBy, String sortDir, String filter) {

        log.info("Getting all employees with page");

        Pageable paging = PageRequest.of(pageNum, pageSize, ascOrDesc(sortDir, sortBy));

        Page<Employee> filteredValue = filter(filter,paging);

        Page<EmployeeDTO> returnValue = filteredValue.map(PageMapping::employeesPageToEmployeeDTOPage);

        return returnValue;
    }

    private Sort ascOrDesc(String sortDir, String sortBy) {
        // if sortDir is null return unsorted
        // if sortDir is asc return ascending
        // if sortDir is desc/other return descending

        return sortDir.equals("asc") ?
                sortBy == null ? Sort.unsorted() :
                        Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    }

    private Page<Employee> filter(String filter, Pageable pageable) {
        return filter == null ? employeeRepository.findAll(pageable) :
                employeeRepository.findByNameContainsOrSurnameContains(filter, filter, pageable);
    }

}

