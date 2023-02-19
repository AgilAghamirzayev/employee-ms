package com.mastercode.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercode.employee.dto.EmployeeDTO;
import com.mastercode.employee.model.CreateEmployeeResponseModel;
import com.mastercode.employee.services.EmployeeService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

@RunWith(value = SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    EmployeeDTO employee = new EmployeeDTO(1L, "Aqil", "Zeka", BigDecimal.valueOf(111));

    @Test
    void getAllEmployees_whenMockMVC_thenResponseOK() throws Exception {
        List<EmployeeDTO> employees = Arrays.asList(employee,
                new EmployeeDTO(2L, "Ali", "Aliyev", BigDecimal.valueOf(1111)));

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mvc.perform(get("/employees")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is(employees.get(1).getName())));
    }

    @Test
    void getEmployeeById_whenMockMVC_thenResponseOK() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mvc.perform(get("/employees/{id}", employee.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(employee.getName())))
                .andExpect(jsonPath("$.surname", is(employee.getSurname())));
    }

    @Test
    void createEmployee_whenMockMVC_thenResponseCREATED() throws Exception {
        mvc.perform(post("/employees")
                .content(asJsonString(new CreateEmployeeResponseModel(1L,"ALIA","ALIA",BigDecimal.ONE)))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateEmployee_whenMockMVC_thenResponseOK() throws Exception {
        mvc.perform(put("/employees/{id}", 1)
                .content(asJsonString(new CreateEmployeeResponseModel(1L,"Aqil","Zeka", BigDecimal.TEN)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployee_whenMockMVC_thenResponseOK() throws Exception {
        mvc.perform(delete("/employees/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getAllEmployeesPage_whenMockMVC_thenResponseOK() throws Exception {
        Page<EmployeeDTO> employees = new PageImpl<>(Arrays.asList(employee,
                new EmployeeDTO(2L, "Ali", "Aliyev", BigDecimal.valueOf(1111))));

        when(employeeService.getAllEmployeesPage(1,10,null,"asc", null)).thenReturn(employees);

        mvc.perform(get("/employees/list")
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("content.length()", is(employees.getSize())))
                .andExpect(jsonPath("content[1].name", is("Ali")))
                .andExpect(jsonPath("content[1].surname", is("Aliyev")))
                .andExpect(jsonPath("totalPages", is(1)))
                .andExpect(jsonPath("totalElements", is(2)))
                .andExpect(jsonPath("sort.sorted", is(false)));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}