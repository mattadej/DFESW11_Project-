package com.qa.employee_manager.repo;

import com.qa.employee_manager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//The reason for this class is to allow us to have access to
//repository methods because they come from the JpaRepository interface.

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}

