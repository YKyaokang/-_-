package com.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.resource.model.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
} 