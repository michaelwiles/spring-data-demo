package com.springdatademo.test;

import org.springframework.stereotype.Component;

import com.springdatademo.model.Department;
import com.springdatademo.model.Employee;
import com.springdatademo.model.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class DataSetup {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(DataSetup.class);

    @PersistenceContext
    EntityManager em;

    public void setup() {

        Department d1 = new Department("development");
        em.persist(d1);
        Department d2 = new Department("operations");
        em.persist(d2);

        Team t1 = new Team("new business");
        Team t2 = new Team("sales portal");

        em.persist(t1);
        em.persist(t2);

        Employee e1 = new Employee(1, "bob", "smith");
        em.persist(e1);
        Employee e2 = new Employee(2, "craig", "smith");
        em.persist(e2);
        Employee e3 = new Employee(3, "bob", "carmichael");
        em.persist(e3);

        t1.add(e1);
        t1.add(e3);

        t2.add(e2);
        t2.add(e3);

        e1.setDepartment(d1);
        e2.setDepartment(d1);
        e3.setDepartment(d2);

        em.flush();
    }

}
