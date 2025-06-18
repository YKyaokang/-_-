package com.resource.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.resource.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("resource_application")
public class ResourceApplication extends BaseEntity {
    @TableField("employee_id")
    private Long employeeId;
    
    @TableField("resource_id")
    private Long resourceId;
    
    private Integer quantity;
    private String purpose;
    
    @TableField("start_date")
    private LocalDate startDate;
    
    @TableField("end_date")
    private LocalDate endDate;
    
    private String status;
    
    @TableField("submit_flag")
    private Integer submitFlag;
    
    @TableField("approval_flag")
    private Integer approvalFlag;
} 