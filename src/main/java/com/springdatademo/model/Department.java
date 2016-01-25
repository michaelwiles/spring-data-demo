package com.springdatademo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Department.class);

    @GeneratedValue
    @Id
    Long id;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    private String name;

    public Department(String name) {
        super();
        this.name = name;
    }

    public Department() {
        super();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Employee employee) {
        employees.add(employee);
        employee.setDepartment0(this);
    }
}
