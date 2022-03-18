package com.qa.employee_manager.entity;


import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeTest {

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Employee.class).usingGetClass().verify();

    }

    @Test
    public void getAndSetTest() {
        Employee employee = new Employee();

        employee.setFirstName("Matthew");
        employee.setSecondName("Adejumo");
        employee.setAge(26);
        employee.setEmail("madejumo@qa.com");
        employee.setGender("Male");

        assertNotNull(employee.getFirstName());
        assertNotNull(employee.getSecondName());
        assertNotNull(employee.getAge());
        assertNotNull(employee.getEmail());
        assertNotNull(employee.getGender());

        assertEquals(employee.getFirstName(), "Matthew");
        assertEquals(employee.getSecondName(), "Adejumo");
        assertEquals(employee.getAge(), 26);
        assertEquals(employee.getEmail(), "madejumo@qa.com");
        assertEquals(employee.getGender(), "Male");

    }

    @Test
    public void allArgsConstructor() {
        Employee employee = new Employee( 1L,"Matthew", "Adejumo", 26, "madejumo@qa.com", "Male");

        assertNotNull(employee.getId());
        assertNotNull(employee.getFirstName());
        assertNotNull(employee.getSecondName());
        assertNotNull(employee.getAge());
        assertNotNull(employee.getEmail());
        assertNotNull(employee.getGender());

        assertEquals(employee.getId(), 1L);
        assertEquals(employee.getFirstName(), "Matthew");
        assertEquals(employee.getSecondName(), "Adejumo");
        assertEquals(employee.getAge(), 26);
        assertEquals(employee.getEmail(), "madejumo@qa.com");
        assertEquals(employee.getGender(), "Male");

    }

    @Test
    public void noIDConstructor() {
        Employee employee = new Employee("Matthew", "Adejumo", 26, "madejumo@qa.com", "Male");

        assertNotNull(employee.getId());
        assertNotNull(employee.getFirstName());
        assertNotNull(employee.getSecondName());
        assertNotNull(employee.getAge());
        assertNotNull(employee.getEmail());
        assertNotNull(employee.getGender());

        assertEquals(employee.getId(), 0);
        assertEquals(employee.getFirstName(), "Matthew");
        assertEquals(employee.getSecondName(), "Adejumo");
        assertEquals(employee.getAge(), 26);
        assertEquals(employee.getEmail(), "madejumo@qa.com");
        assertEquals(employee.getGender(), "Male");
    }

}
