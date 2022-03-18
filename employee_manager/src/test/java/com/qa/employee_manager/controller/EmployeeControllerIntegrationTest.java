package com.qa.employee_manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.employee_manager.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Boots the context
//running on a random port instead of 8080 in case something is running on it
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //creates the MockMvc object
@ActiveProfiles("test") //sets current profile to 'test'
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:employee-schema.sql",
        "classpath:employee-data.sql"})
public class EmployeeControllerIntegrationTest {

    @Autowired //tells Spring to insert this object into the class
    private MockMvc mvc; //object for running fake requests

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testCreate() throws Exception {
        // URL body method headers
        Employee testEmployee = new Employee("Matthew", "Adejumo", 26, "madejumo@qa.com", "Male");
        String testEmployeeAsJSON = this.mapper.writeValueAsString(testEmployee);
        RequestBuilder req = post("/employee/create").content(testEmployeeAsJSON).contentType(MediaType.APPLICATION_JSON);

        Employee testSavedEmployee = new Employee(2,"Matthew", "Adejumo", 26, "madejumo@qa.com", "Male");
        String testSavedEmployeeAsJSON = this.mapper.writeValueAsString(testSavedEmployee);
        // this will check the status code of my response
        ResultMatcher checkStatus = status().isCreated();
        // this will check the body of the response
        ResultMatcher checkBody = content().json(testSavedEmployeeAsJSON);

        // run the request and check both matchers
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    public void testCreate2() throws Exception {
        // URL body method headers
        Employee testEmployee = new Employee("Adejumo", "Matthew", 26, "madejumo@qa.com", "Male");
        String testEmployeeAsJSON = this.mapper.writeValueAsString(testEmployee);
        RequestBuilder req = post("/employee/create").content(testEmployeeAsJSON).contentType(MediaType.APPLICATION_JSON);

        Employee testSavedEmployee = new Employee(2,"Adejumo", "Matthew", 26, "madejumo@qa.com", "Male");
        String testSavedEmployeeAsJSON = this.mapper.writeValueAsString(testSavedEmployee);
        // this will check the status code of my response
        ResultMatcher checkStatus = status().isCreated();
        // this will check the body of the response
        ResultMatcher checkBody = content().json(testSavedEmployeeAsJSON);

        // run the request and check both matchers
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    public void testReadById() throws Exception {
        RequestBuilder req = get("/employee/readById/1");

        ResultMatcher checkStatus = status().isOk();

        Employee savedEmployee = new Employee(1,"Mike", "Oxmaul", 56, "moxmaul@qa.com", "Male");
        String savedEmployeeAsJSON = this.mapper.writeValueAsString(savedEmployee);

        ResultMatcher checkBody = content().json(savedEmployeeAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    public void testReadAll() throws Exception {
        Employee entry = new Employee(1,"Mike", "Oxmaul", 56, "moxmaul@qa.com", "Male");
        List<Employee> employee = new ArrayList<>();
        employee.add(entry);
        String employeeOutputAsJson = this.mapper.writeValueAsString(employee);

        this.mvc.perform(get("/employee/readAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(employeeOutputAsJson));
    }

    @Test
    public void testUpdate() throws Exception {
        Employee entry = new Employee(1L,"Anita", "Bath", 21, "abath@qa.com", "Female");
        String entryEmployeeAsJson = this.mapper.writeValueAsString(entry);

        RequestBuilder req = put("/employee/update/1").content(entryEmployeeAsJson).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = status().isAccepted();
        ResultMatcher checkBody = content().json(entryEmployeeAsJson);

        mvc.perform(req).andExpect(checkBody).andExpect(checkStatus);


    }

    @Test
    public void testDelete() throws Exception {
        RequestBuilder req = delete("/employee/delete/1");

        ResultMatcher checkStatus = status().isNoContent();

        this.mvc.perform(req).andExpect(checkStatus);
    }


}


