package com.springdatademo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Employee.class);

    @GeneratedValue
    @Id
    Long id;

    private int employeeId;
    private String firstName;
    private String surname;

    @ManyToMany(mappedBy = "members")
    private List<Team> teams = new ArrayList<>();

    @ManyToOne
    private Department department;

    Employee() {
    }

    public Employee(int employeeId, String firstName, String surname) {
        super();
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Department getDepartment() {
        return department;
    }

    void setDepartment0(Department department) {
        this.department = department;
    }

    public void setDepartment(Department department) {
        department.add(this);
    }

    void add0(Team team) {
        this.teams.add(team);
    }

    public void add(Team team) {
        team.add(this);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", employeeId=" + employeeId + ", firstName=" + firstName + ", surname=" + surname + "]";
    }
}
