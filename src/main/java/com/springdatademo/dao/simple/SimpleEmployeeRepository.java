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

    /**
     * returns employees by surname
     */
    public List<Employee> findBySurname(String surname) {
        Query query = em.createQuery("select e from Employee e where e.surname = ?1");
        query.setParameter(1, surname);
        return query.getResultList();
    }

    /**
     * returns all employees in the team with the given name
     */
    public List<Employee> findByTeamName(String teamName) {
        Query query = em.createQuery("select e from Employee e join e.teams t where t.name = ?1");
        query.setParameter(1, teamName);
        return query.getResultList();
    }

}
