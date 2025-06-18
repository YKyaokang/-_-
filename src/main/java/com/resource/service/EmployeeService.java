package com.resource.service;

import com.resource.model.dto.LoginDTO;
import com.resource.model.dto.RegisterDTO;
import com.resource.model.entity.Employee;

public interface EmployeeService {
    Employee register(RegisterDTO registerDTO);
    Employee login(LoginDTO loginDTO);
    void logout();
    Employee getCurrentEmployee();
} 