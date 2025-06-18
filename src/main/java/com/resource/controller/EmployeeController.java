package com.resource.controller;

import com.resource.common.Result;
import com.resource.model.dto.LoginDTO;
import com.resource.model.dto.RegisterDTO;
import com.resource.model.entity.Employee;
import com.resource.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public Result<Employee> register(@RequestBody RegisterDTO registerDTO) {
        try {
            Employee employee = employeeService.register(registerDTO);
            return Result.success(employee);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Result<Employee> login(@RequestBody LoginDTO loginDTO) {
        try {
            Employee employee = employeeService.login(loginDTO);
            return Result.success(employee);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public Result<Void> logout() {
        try {
            employeeService.logout();
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/current")
    public Result<Employee> getCurrentEmployee() {
        try {
            Employee employee = employeeService.getCurrentEmployee();
            return Result.success(employee);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 