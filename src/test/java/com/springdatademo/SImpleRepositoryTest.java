package com.springdatademo;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Lists;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.springdatademo.dao.simple.SimpleEmployeeRepository;
import com.springdatademo.model.Department;
import com.springdatademo.model.Employee;
import com.springdatademo.model.Team;

import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SImpleRepositoryTest {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SImpleRepositoryTest.class);

    @PersistenceContext
    EntityManager em;

    @Inject
    SimpleEmployeeRepository simplePersonRepository;

    @Rule
    public MethodRule rule = new MethodRule() {

        @Override
        public Statement apply(Statement base, FrameworkMethod method, Object target) {
            return new Statement() {

                @Override
                public void evaluate() throws Throwable {
                    System.out.println("\n\n***");
                    base.evaluate();
                    System.out.println("***\n");

                }
            };

        }
    };

    @Before
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

    @Test
    @Transactional
    public void testFindBySurname() {
        LOG.info("find by surname");
        List<Employee> findBySurname = simplePersonRepository.findBySurname("smith");
        LOG.debug("found {}", findBySurname);
        assertThat(findBySurname).areAtLeast(2, new Condition<>(employee -> employee.getSurname().equals("smith"), "Surname %s not found", "smith"));
    }

    @Test
    @Transactional
    public void testFindByTeam() {

        LOG.info("find by team");
        List<Employee> findByTeam = simplePersonRepository.findByTeamName("new business");
        ArrayList<Integer> team1EmployeeIds = Lists.newArrayList(1, 3);
        LOG.debug("found employtees {}", findByTeam);
        assertThat(findByTeam).areExactly(2, new Condition<>(employee -> team1EmployeeIds.contains(employee.getEmployeeId()), "team %s not found", team1EmployeeIds));
    }
}
