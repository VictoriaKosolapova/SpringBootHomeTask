package com.netcracker.edu.db.employee.service;

import com.netcracker.edu.db.employee.dao.EmployeeDao;
import com.netcracker.edu.db.employee.model.Employee;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigInteger;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Employee getEmployeeById(BigInteger employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    @Transactional
    public boolean addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    @Transactional
    public boolean deleteEmployee(Employee employee) {
        if(employeeDao.getEmployeeById(employee.getId()) == null){
            throw new ResourceNotFoundException("employee "+employee.getId()+" not found");
        }
        else return employeeDao.deleteEmployee(employee);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean swapEmployeesPositionsAndSalaries(BigInteger promotedId, BigInteger demotedId) {
        Employee employeePromoted = employeeDao.getEmployeeById(promotedId);
        Employee employeeDemoted = employeeDao.getEmployeeById(demotedId);

        if(employeePromoted == null){
            throw new ResourceNotFoundException("employee "+promotedId+" not found");
        }
        if(employeeDemoted == null){
            throw new ResourceNotFoundException("employee "+demotedId+" not found");
        }

            long salaryExchange = employeeDao.getEmployeeById(promotedId).getSalary();
            String positionExchange = employeeDao.getEmployeeById(promotedId).getPosition();

            employeePromoted.setPosition(employeeDao.getEmployeeById(demotedId).getPosition());
            employeeDemoted.setPosition(positionExchange);

            employeePromoted.setSalary(employeeDao.getEmployeeById(demotedId).getSalary());
            employeeDemoted.setSalary(salaryExchange);
            return employeeDao.updateEmployee(employeePromoted)
                    && employeeDao.updateEmployee(employeeDemoted);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
}
