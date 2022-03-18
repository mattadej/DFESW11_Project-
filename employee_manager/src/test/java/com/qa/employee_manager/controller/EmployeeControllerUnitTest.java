package com.qa.employee_manager.controller;



import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.employee_manager.entity.Employee;
import com.qa.employee_manager.service.EmployeeService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerUnitTest {

    @Autowired
    private EmployeeController controller;

    @MockBean
    private EmployeeService service;

    @Test
    public void createEmployeeTest() {
        Employee employee = new Employee("Michael", "Jordan", 60, "mj@qa.com", "Male");

        Mockito.when(this.service.create(employee)).thenReturn(employee);

        ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.CREATED);

        assertThat(response).isEqualTo(this.controller.createEmployee(employee));

        Mockito.verify(this.service, times(1)).create(employee);
    }

    @Test
    public void testUpdate() {
        Employee employeeUpdate = new Employee("Michael", "Jordan", 60, "mj@qa.com", "Male");

        Mockito.when(this.service.update(1L, employeeUpdate)).thenReturn(employeeUpdate);

        ResponseEntity<Employee> responseEntity = new ResponseEntity<>(employeeUpdate, HttpStatus.ACCEPTED);

        assertEquals(responseEntity, this.controller.updateEmployee(1L, employeeUpdate));

        Mockito.verify(this.service, Mockito.times(1)).update(1L, employeeUpdate);
    }
}