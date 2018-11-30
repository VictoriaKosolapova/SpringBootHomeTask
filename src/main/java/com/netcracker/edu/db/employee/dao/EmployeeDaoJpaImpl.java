package com.netcracker.edu.db.employee.dao;

import com.netcracker.edu.db.employee.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

/**
 * TODO: Implement me using Hibernate
 */
@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee getEmployeeById(BigInteger employeeId) {
        return entityManager.find(Employee.class, employeeId);
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
}
