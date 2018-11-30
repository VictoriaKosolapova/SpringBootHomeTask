package com.netcracker.edu.db.employee.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 *  id   name    surname     position    departmentId    salary
 *  -----------------------------------------------------------
 *  1    name1   surname1    boss        1               100
 *  2    name2   surname2    big boss    1               200
 *  3    name3   surname2    big boss    1               300
 *  4    name4   surname3    boss        2               100
 *  5    name5   surname4    boss        2               100
 */
public class EmployeeDaoJdbcTest {

    private EmployeeDao employeeDao = new EmployeeDaoJdbcImpl("jdbc:h2:mem:testdb", "sa", "");

    private static EmbeddedDatabase database;

    @BeforeClass
    public static void setup() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb")
                .addScript("createAndFillTestDb.sql")
                .build();
    }

    @AfterClass
    public static void tearDown() {
        database.shutdown();
    }
}
