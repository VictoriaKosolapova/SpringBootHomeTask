package com.netcracker.edu.db.employee.dao;

import com.netcracker.edu.db.employee.model.Employee;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;

/**
 * TODO: Implement me using Spring jdbc
 */
public class EmployeeDaoJdbcImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDaoJdbcImpl(String url, String username, String password) {
        DataSource dataSource = createDataSource(url, username, password);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Employee getEmployeeById(BigInteger employeeId) {
        return null;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return false;
    }

    @Override
    public List<Employee> getEmployeesBySurname(String surname) {
        return null;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(long departmentId) {
        return null;
    }

    @Override
    public List<Employee> getEmployeesWithGreaterSalary(long thresholdSalary) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    private DataSource createDataSource(String url, String username, String password) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        return dataSourceBuilder.build();
    }
}
