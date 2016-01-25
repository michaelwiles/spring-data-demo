package com.springdatademo.dao.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springdatademo.model.Employee;

import java.util.List;

public interface SpringDataEmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySurname(String string);

    List<Employee> findByTeamsName(String string);

}
