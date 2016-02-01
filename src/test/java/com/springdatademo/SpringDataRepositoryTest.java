package com.springdatademo;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Lists;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.springdatademo.dao.springdata.EmployeeRepository;
import com.springdatademo.model.Employee;
import com.springdatademo.test.DataSetup;
import com.springdatademo.test.StarOutput;

import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SpringDataRepositoryTest {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SpringDataRepositoryTest.class);

    @Rule
    public MethodRule rule = new StarOutput();

    @PersistenceContext
    EntityManager em;

    @Inject
    DataSetup dataSetup;

    @Inject
    EmployeeRepository simplePersonRepository;

    @Before
    public void populateData() {
        dataSetup.setup();
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
        List<Employee> findByTeam = simplePersonRepository.findByTeamsName("new business");
        ArrayList<Integer> team1EmployeeIds = Lists.newArrayList(1, 3);
        LOG.debug("found employtees {}", findByTeam);
        assertThat(findByTeam).areExactly(2, new Condition<>(employee -> team1EmployeeIds.contains(employee.getEmployeeId()), "team %s not found", team1EmployeeIds));
    }
}
