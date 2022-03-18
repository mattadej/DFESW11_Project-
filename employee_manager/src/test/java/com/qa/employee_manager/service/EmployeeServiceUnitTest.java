package com.qa.employee_manager.service;

import com.qa.employee_manager.entity.Employee;
import com.qa.employee_manager.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeServiceUnitTest {

    @Autowired
    private EmployeeService service;

    //Mocking the repository class, so we don't have to rely on the class itself
    @MockBean
    private EmployeeRepo repo;

    @Test
    public void createEmployeeTest() {
        Employee input = new Employee("Matthew", "Adejumo", 26, "madejumo@qa.com", "Male");
        Employee output = new Employee(1L, "Matthew", "Adejumo", 26, "madejumo@qa.com", "Male");

        Mockito.when(this.repo.save(input)).thenReturn(output);

        assertEquals(output, this.service.create(input));

        Mockito.verify(this.repo, Mockito.times(1)).save(input);
    }

    @Test
    public void readByIdTest() {

        Optional<Employee> optionalOutput = Optional.of(new Employee(1L, "Matthew", "Adejumo", 26, "madejumo@qa.com", "Male"));
        Employee output = new Employee(1L, "Matthew", "Adejumo", 26, "madejumo@qa.com", "Male");

        Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOutput);

        assertEquals(output, this.service.readById(Mockito.anyLong()));

        Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void deleteTrueTest() {
        Mockito.when(this.repo.existsById(1L)).thenReturn(false);

        assertTrue(this.service.delete(1L));

        Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
        Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
    }
}
