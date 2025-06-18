package com.resource.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.resource.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("employee")
public class Employee extends BaseEntity {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String department;
    private String position;
    private String role;
} 