package com.resource.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.resource.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("resource")
public class Resource extends BaseEntity {
    private String category;
    private String categoryCode;
    private String name;
    private Integer quantity;
    private String status;
} 