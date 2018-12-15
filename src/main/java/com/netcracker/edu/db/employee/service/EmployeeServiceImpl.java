package com.netcracker.edu.db.employee.service;

import com.netcracker.edu.db.employee.dao.EmployeeDao;
import com.netcracker.edu.db.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee getEmployeeById(BigInteger employeeId) {
        return employeeDao.getEmployeeById(employeeId);
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
    public boolean swapEmployeesPositionsAndSalaries(BigInteger promotedId, BigInteger demotedId) {
        return false;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }
}
