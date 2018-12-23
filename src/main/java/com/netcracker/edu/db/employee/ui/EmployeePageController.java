package com.netcracker.edu.db.employee.ui;

import com.netcracker.edu.db.employee.model.Employee;
import com.netcracker.edu.db.employee.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@Controller
public class EmployeePageController {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String EMPLOYEE_ATTR = "employee";

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/find")
    public String findById(Model model, @RequestParam(value="id", required=false) String id) {
        if (!ControllerUtils.isFilled(id)) {
            LOGGER.warn("Employee ID was not specified: {}", id);
            model.addAttribute(EMPLOYEE_ATTR, null);
            return "find";
        }

        BigInteger employeeId = ControllerUtils.toBigInteger(id);
        Employee employee = employeeId != null ? employeeService.getEmployeeById(employeeId) : null;
        employee = employee != null ? employee : ControllerUtils.getNonexistentEmployee();

        model.addAttribute(EMPLOYEE_ATTR, employee);

        return "find";
    }

    @GetMapping("/all")
    public String greeting(Model model) {
        // This stub implementation should be replaced by employeeService method invocation
        List<Employee> employeeList = ControllerUtils.getStubEmployeeList();

        model.addAttribute("employees", employeeList);

        return "all";
    }
}
