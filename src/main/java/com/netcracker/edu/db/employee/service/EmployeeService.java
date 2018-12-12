package com.netcracker.edu.db.employee.service;

import com.netcracker.edu.db.employee.model.Employee;

import java.math.BigInteger;

public interface EmployeeService {
    Employee getEmployeeById(BigInteger employeeId);
}
