package com.springdatademo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Team.class);

    @GeneratedValue
    @Id
    Long id;

    private String name;

    @ManyToMany
    private List<Employee> members = new ArrayList<>();

    public Team(String name) {
        super();
        this.name = name;
    }

    Team() {
        super();
    }

    public List<Employee> getMembers() {
        return members;
    }

    public void setMembers(List<Employee> members) {
        this.members = members;
    }

    public void add(Employee employee) {
        this.members.add(employee);
        employee.add0(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
