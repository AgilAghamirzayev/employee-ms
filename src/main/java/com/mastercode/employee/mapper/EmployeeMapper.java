package com.mastercode.employee.mapper;

import com.mastercode.employee.model.CreateEmployeeRequestModel;
import com.mastercode.employee.model.CreateEmployeeResponseModel;
import com.mastercode.employee.dto.EmployeeDTO;
import com.mastercode.employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeDtoToEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO createEmployeeRequestModelToEmployeeDTO(CreateEmployeeRequestModel employeeDetails);

    List<EmployeeDTO> employeeListToEmployeeDTOList(Iterable<Employee> employeeDTO);

    CreateEmployeeResponseModel employeeToCreateEmployeeResponseModel(EmployeeDTO employeeDTO);

    List<CreateEmployeeResponseModel> employeesDTOListToCreateEmployeeResponseModelList(List<EmployeeDTO> employeesDTOList);

    List<Employee> employeesDTOListToEmployeeList(List<EmployeeDTO> allEmployees);
}
