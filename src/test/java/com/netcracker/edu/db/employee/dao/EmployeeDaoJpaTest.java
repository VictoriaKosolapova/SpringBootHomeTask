package com.netcracker.edu.db.employee.dao;

import com.netcracker.edu.db.employee.model.Employee;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 *  id   name    surname     position    departmentId    salary
 *  -----------------------------------------------------------
 *  1    name1   surname1    boss        1               100
 *  2    name2   surname2    big boss    1               200
 *  3    name3   surname2    big boss    1               300
 *  4    name4   surname3    boss        2               100
 *  5    name5   surname4    boss        2               100
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeDaoJpaTest {

    @Autowired
    private EmployeeDao employeeDao;

    private static EmbeddedDatabase database;

    public static List<Employee> employees;


    static{
        Employee employee1 = new Employee(
                BigInteger.valueOf(1L), "name1", "surname1", "boss", 1L, 100);

        Employee employee2 = new Employee(
                BigInteger.valueOf(2L), "name2", "surname2", "big boss", 1L, 200);

        Employee employee3 = new Employee(
                BigInteger.valueOf(3L), "name3", "surname2", "big boss", 1L, 300);

        Employee employee4 = new Employee(
                BigInteger.valueOf(4L), "name4", "surname3", "boss", 2L, 100);

        Employee employee5 = new Employee(
                BigInteger.valueOf(5L), "name5", "surname4", "boss", 2L, 100);

        employees = Arrays.asList(employee1, employee2, employee3, employee4, employee5);

    }

    @BeforeClass
    public static void setup() throws SQLException {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb")
                .addScript("createTestDb.sql")
                .build();
    }

    @Before
    public void setupData() throws Exception {
        database.getConnection().createStatement().execute(getContentByResourceRelativePath("deleteData.sql"));
        database.getConnection().createStatement().execute(getContentByResourceRelativePath("fillData.sql"));
    }

    @AfterClass
    public static void tearDown() {
        database.shutdown();
    }

    @Test
    public void testGetEmployeeByIdExist() {
        Employee actual = employeeDao.getEmployeeById(BigInteger.valueOf(1L));
        Employee expected = new Employee(
                BigInteger.valueOf(1L), "name1", "surname1", "boss", 1L, 100);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetEmployeeByIdNotExist() {
        Employee actual = employeeDao.getEmployeeById(BigInteger.valueOf(10L));
        Assert.assertNull(actual);
    }

    @Test
    public void testAddEmployee() {
        Employee addedEmployee = new Employee(null, "name10", "surname10", "manager", 5L, 200);
        boolean actual = employeeDao.addEmployee(addedEmployee);
        Assert.assertTrue(actual);
    }


    @Test
    public void testDeleteEmployeeByIdExist(){
        Employee delitedEmployee = new Employee(
                BigInteger.valueOf(2L),"name2","surname2","big boss",1,200);
        boolean actual = employeeDao.deleteEmployee(delitedEmployee);
        System.out.println(actual);
        Assert.assertTrue(actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testDeleteEmployeeByIdNotExist(){
        Employee delitedEmployee = new Employee(
                BigInteger.valueOf(10L),"name2","surname2","big boss",1,200);
        boolean actual = employeeDao.deleteEmployee(delitedEmployee);
        Assert.assertFalse(actual);
    }
    @Test
    public void testGetEmployeeBySurname(){
        Assert.assertArrayEquals(employeeDao.getEmployeesBySurname("surname2").toArray(),employees.subList(1,3).toArray());

    }

    @Test
    public void testGetEmployeesByDepartmentId(){
        Assert.assertArrayEquals(employeeDao.getEmployeesByDepartmentId(2).toArray(),employees.subList(3,5).toArray());

    }

    @Test
    public void testGetEmployeesWithGreaterSalary(){
        Assert.assertArrayEquals(employeeDao.getEmployeesWithGreaterSalary(100).toArray(),employees.subList(1,3).toArray());

    }

    @Test
    public void testGetAllEmployees() {
        for (Employee e : employeeDao.getAllEmployees()) {
            System.out.println(e.getName());
        }
        Assert.assertArrayEquals(employeeDao.getAllEmployees().toArray(),employees.toArray());
    }


    private String getContentByResourceRelativePath(String path) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
    }
}
