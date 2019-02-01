package com.netcracker.edu.db.employee.web;

import com.netcracker.edu.db.employee.model.Employee;
import com.netcracker.edu.db.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") BigInteger employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @PostMapping
    public boolean addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }
    @PutMapping
    public boolean updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping
    public boolean deleteEmployee(@RequestBody Employee employee){
        return employeeService.deleteEmployee(employee);
    }

    @PutMapping("/swapEmployeesPositionsAndSalaries")
    public boolean swapEmployeesPositionsAndSalaries(@RequestParam(value = "promotedId",required = true) BigInteger promotedId,
                                                     @RequestParam(value = "demotedId",required = true) BigInteger demotedId){
        return employeeService.swapEmployeesPositionsAndSalaries(promotedId,demotedId);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    // Add here REST services for all declared service methods
}