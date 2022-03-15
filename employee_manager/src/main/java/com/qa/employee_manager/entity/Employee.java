package com.qa.employee_manager.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
public class Employee {

    //This is the ID column, and ID is generated using @GeneratedValue
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //This is the first name column, it can't be null
    @Column(nullable = false)
    private String firstName;

    //This is the second name column, it can't be null
    @Column(nullable = false)
    private String secondName;

    //The minimum age for an employee is 18, and maximum is 65
    @Column
    @Min(18)
    @Max(65)
    private int age;

    //The validation returns true as long as there is an @ symbol in the email address
    @Column
    @Pattern(regexp = "^(.+)@(\\\\S+)$")
    private String email;

    @Column
    private String gender;

    //Default constructor
    public Employee() {
    }

    //For creating employees
    public Employee(String firstName, String secondName, @Min(18) @Max(65) int age, String email, String gender) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

    //For testing
    public Employee(long id, String firstName, String secondName, @Min(18) @Max(65) int age, String email, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    //More for testing when comparing objects match
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && firstName.equals(employee.firstName) &&
                secondName.equals(employee.secondName) &&
                Objects.equals(email, employee.email) && Objects.equals(gender, employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, age, email, gender);
    }
}
