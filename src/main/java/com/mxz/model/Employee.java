package com.mxz.model;
 

import java.math.BigDecimal;

public class Employee {
    private Integer id;

    private Integer employeeId;
 
    private String firstName;
 
    private String lastName;
 
    private String email;
 
    private BigDecimal salary;

    public Employee(Integer employeeId, String firstName, String lastName, String email, BigDecimal salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }
 
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public BigDecimal getSalary() {
        return salary;
    }
 
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}