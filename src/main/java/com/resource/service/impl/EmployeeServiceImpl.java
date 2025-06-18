package com.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.resource.mapper.EmployeeMapper;
import com.resource.model.dto.LoginDTO;
import com.resource.model.dto.RegisterDTO;
import com.resource.model.entity.Employee;
import com.resource.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee register(RegisterDTO registerDTO) {
        // 检查用户名是否已存在
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, registerDTO.getUsername());
        if (employeeMapper.selectCount(queryWrapper) > 0) {
            throw new RuntimeException("用户名已存在");
        }

        // 创建新员工
        Employee employee = new Employee();
        BeanUtils.copyProperties(registerDTO, employee);
        
        // 加密密码
        employee.setPassword(DigestUtils.md5DigestAsHex(registerDTO.getPassword().getBytes()));
        employee.setRole("EMPLOYEE");

        employeeMapper.insert(employee);
        return employee;
    }

    @Override
    public Employee login(LoginDTO loginDTO) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, loginDTO.getUsername())
                   .eq(Employee::getPassword, DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes()));
        
        Employee employee = employeeMapper.selectOne(queryWrapper);
        if (employee == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        return employee;
    }

    @Override
    public void logout() {
        // 由于没有使用session或token，这里暂时不需要实现
    }

    @Override
    public Employee getCurrentEmployee() {
        // 由于没有使用session或token，这里暂时返回null
        return null;
    }
} 