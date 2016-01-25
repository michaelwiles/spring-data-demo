package com.springdatademo.dao.simple;

import org.springframework.stereotype.Component;

import com.springdatademo.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

@Component
public class SimpleEmployeeRepository {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SimpleEmployeeRepository.class);

    @PersistenceContext
    EntityManager em;

    // employees by name
    public List<Employee> findBySurname(String surname) {
        Query findBySurname = em.createQuery("select p from Employee p  where p.surname = ?1");
        findBySurname.setParameter(1, surname);
        return findBySurname.getResultList();
    }

    // employees by team
    public List<Employee> findByTeamName(String teamName) {
        Query findBySurname = em.createQuery("select e from Employee e join e.teams t where t.name = ?1");
        findBySurname.setParameter(1, teamName);
        return findBySurname.getResultList();
    }

}
