package com.netcracker.edu.db.employee.dao;

import com.netcracker.edu.db.employee.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

/**
 * TODO: Implement me
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
    @Transactional
    public boolean addEmployee(Employee employee) {
            entityManager.persist(employee);
            return entityManager.find(Employee.class, employee.getId()) != null;

    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Employee updatedEmployee = entityManager.find(Employee.class, employee.getId());
        if(entityManager.find(Employee.class, employee.getId()) == null)
            return false;
        else {
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setSurname(employee.getSurname());
            updatedEmployee.setSalary(employee.getSalary());
            updatedEmployee.setPosition(employee.getPosition());
            updatedEmployee.setDepartmentId(employee.getDepartmentId());
            entityManager.flush();
            return true;
        }
    }

    @Override
    @Transactional
    public boolean deleteEmployee(Employee employee) {
        Employee deletedEmployee = entityManager.find(Employee.class, employee.getId());
        entityManager.remove(deletedEmployee);
        return entityManager.find(Employee.class, employee.getId()) == null;
    }

    @Override
    @Transactional
    public List<Employee> getEmployeesBySurname(String surname) {
        String queryString = "SELECT e FROM Employee e " +
                "WHERE (e.surname) = :surname";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Employee> getEmployeesByDepartmentId(long departmentId) {
        String queryString = "SELECT e FROM Employee e " +
                "WHERE (e.departmentId) = :departmentId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("departmentId", departmentId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Employee> getEmployeesWithGreaterSalary(long thresholdSalary) {
        String queryString = "SELECT e FROM Employee e " +
                "WHERE (e.salary) > :thresholdSalary";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("thresholdSalary", thresholdSalary);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return entityManager.createQuery(
                "SELECT e FROM Employee as e",Employee.class).getResultList();

    }

}
