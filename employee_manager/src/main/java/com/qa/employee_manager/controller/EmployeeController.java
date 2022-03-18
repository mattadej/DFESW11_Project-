package com.qa.employee_manager.controller;

import com.qa.employee_manager.entity.Employee;
import com.qa.employee_manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    //instructs Spring to insert the EmployeeService object
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    //Create
    @PostMapping("/create")
    // @RequestBody allows us to pass through an object/data when we make the
    // request
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(this.service.create(employee), HttpStatus.CREATED);
    }

    //Real All
    @GetMapping("/readAll")
    // We return a list because the readAll method in the service class returns a
    // list also
    public ResponseEntity<List<Employee>> readAllEmployees() {
        return new ResponseEntity<List<Employee>>(this.service.readAll(), HttpStatus.OK);
    }

    //Read By ID
    @GetMapping("/readById/{id}")
    // @PathVariable allows us to pass a variable (in this case ID) to the path &
    // service.readById method
    public ResponseEntity<Employee> readById(@PathVariable long id) {
        return new ResponseEntity<Employee>(this.service.readById(id), HttpStatus.OK);
    }

    //Update
    @PutMapping("/update/{id}")
    // update requires both RequestBody and PathVariable as it takes in the id, and
    // also passes through the new object information
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(this.service.update(id, employee), HttpStatus.ACCEPTED);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable long id) {
        //Ternary operator, IF  service.delete is successful, return no content
        //ELSE return not found
        return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }
}
