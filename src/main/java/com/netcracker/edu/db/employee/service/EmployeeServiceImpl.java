package com.netcracker.edu.db.employee.service;

import com.netcracker.edu.db.employee.dao.EmployeeDao;
import com.netcracker.edu.db.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee getEmployeeById(BigInteger employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

}
