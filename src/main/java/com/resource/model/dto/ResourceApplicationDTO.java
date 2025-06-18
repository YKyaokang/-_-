package com.resource.model.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ResourceApplicationDTO {
    private Long employeeId;    // 申请人ID
    private Long resourceId;
    private Integer quantity;
    private String purpose;
    private LocalDate startDate;
    private LocalDate endDate;
} 