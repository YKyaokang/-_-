package com.resource.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.resource.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("service_evaluation")
public class ServiceEvaluation extends BaseEntity {
    @TableField("employee_id")
    private Long employeeId;
    
    @TableField("application_id")
    private Long applicationId;
    
    private Integer rating;
    private String comment;
} 