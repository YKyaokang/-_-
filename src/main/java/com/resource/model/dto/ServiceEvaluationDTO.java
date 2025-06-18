package com.resource.model.dto;

import lombok.Data;

@Data
public class ServiceEvaluationDTO {
    private Long employeeId;    // 评价人ID
    private Long applicationId;
    private Integer rating;
    private String comment;
} 