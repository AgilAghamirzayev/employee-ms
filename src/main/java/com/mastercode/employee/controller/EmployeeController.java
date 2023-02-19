package com.mastercode.employee.controller;

import com.mastercode.employee.dto.EmployeeDTO;
import com.mastercode.employee.mapper.EmployeeMapper;
import com.mastercode.employee.mapper.PageMapping;
import com.mastercode.employee.model.CreateEmployeeRequestModel;
import com.mastercode.employee.model.CreateEmployeeResponseModel;
import com.mastercode.employee.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private static final EmployeeMapper MAPPER = EmployeeMapper.INSTANCE;

    private final EmployeeService employeeService;

    @GetMapping
    ResponseEntity<List<CreateEmployeeResponseModel>> getAllEmployees() {

        List<EmployeeDTO> employeesDTOList = employeeService.getAllEmployees();
        List<CreateEmployeeResponseModel> returnValue =
                MAPPER.employeesDTOListToCreateEmployeeResponseModelList(employeesDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping("/{id}")
    ResponseEntity<CreateEmployeeResponseModel> getEmployeeById(@PathVariable("id") Long id) {

        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        CreateEmployeeResponseModel returnValue = MAPPER.employeeToCreateEmployeeResponseModel(employeeDTO);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @PostMapping
    ResponseEntity<CreateEmployeeResponseModel> createEmployee(@RequestBody @Valid CreateEmployeeRequestModel employeeDetails) {

        EmployeeDTO savingValue = MAPPER.createEmployeeRequestModelToEmployeeDTO(employeeDetails);
        EmployeeDTO employee = employeeService.createEmployee(savingValue);
        CreateEmployeeResponseModel returnValue = MAPPER.employeeToCreateEmployeeResponseModel(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PutMapping("/{id}")
    void updateEmployee(@PathVariable("id") Long id,
                        @RequestBody @Valid CreateEmployeeRequestModel employeeDetails) {
        EmployeeDTO updatingValue = MAPPER.createEmployeeRequestModelToEmployeeDTO(employeeDetails);
        employeeService.updateEmployee(id, updatingValue);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/list")
    ResponseEntity<Page<CreateEmployeeResponseModel>> getAllEmployeesPage(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                          @RequestParam(name = "sortBy", required = false) String sortBy,
                                                                          @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                                                                          @RequestParam(name = "filter", required = false) String filter) {

        Page<EmployeeDTO> employeeDTOPage =
                employeeService.getAllEmployeesPage(pageNum, pageSize, sortBy, sortDir, filter);

        Page<CreateEmployeeResponseModel> returnValue =
                employeeDTOPage.map(PageMapping::employeesDTOPageToCreateEmployeeResponseModel);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

}
