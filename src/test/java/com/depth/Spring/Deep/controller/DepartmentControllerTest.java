package com.depth.Spring.Deep.controller;

import com.depth.Spring.Deep.entity.Department;
import com.depth.Spring.Deep.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(DepartmentController.class) // Specify the controller class tested
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @MockitoBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("Cyber Security")
                .departmentAddress("IT-PARK")
                .departmentCode("CY-08")
                .departmentId(1L)
                .build();
    }

    @Test
    @DisplayName("Save Department Data on Controller Layer Testing")
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("Cyber Security")
                .departmentAddress("IT-PARK")
                .departmentCode("CY-08")
                .build();

        Mockito.when(departmentService.savedDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departmentName\":\"Cyber Security\",\"departmentAddress\":\"IT-PARK\",\"departmentCode\":\"CY-08\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Success Fetch Department by id on Department Controller")
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(Optional.ofNullable(department));

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}
