package com.qa.employee_manager.service;

import com.qa.employee_manager.entity.Employee;
import com.qa.employee_manager.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements ServiceMethods<Employee> {

    //Not creating a new object, creating a variable of the type EmployeeRepo
    private EmployeeRepo repo;

    //Above is different to this, aas we are trying to instantiate an object
    //below (Cannot instantiate an interface) EmployeeRepo repo = new EmployeeRepo();

    //Constructor
    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    @Override
    public Employee create(Employee employee) {
        return this.repo.save(employee);
    }

    @Override
    public List<Employee> readAll() {
        return this.repo.findAll();
    }

    @Override
    public Employee readById(long id) {
        Optional<Employee> getEmployee = this.repo.findById(id);
        return getEmployee.orElse(null);
    }

    //When setting values, do not set the ID
    @Override
    public Employee update(long id, Employee employee) {
        Optional<Employee> existingEmployee = this.repo.findById(id);
        if(existingEmployee.isPresent()) {
            Employee exists = existingEmployee.get();
            exists.setAge(employee.getAge());
            exists.setEmail(employee.getEmail());
            exists.setFirstName(employee.getFirstName());
            exists.setGender(employee.getGender());
            exists.setSecondName(employee.getSecondName());

            return this.repo.saveAndFlush(exists);
        }
        return null;
    }

    //Deletes the id, and checks to see if it still exists
    // (should return true if it has worked)
    @Override
    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
}
