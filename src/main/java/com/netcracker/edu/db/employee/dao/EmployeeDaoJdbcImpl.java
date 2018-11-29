package com.netcracker.edu.db.employee.dao;

import com.netcracker.edu.db.employee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoJdbcImpl implements EmployeeDao {

    @Override
    public Employee getEmployeeById(long employeeId) {
        return null;
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
    public List<Employee> getEmployeeWithGreaterSalary(long thresholdSalary) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }
}
