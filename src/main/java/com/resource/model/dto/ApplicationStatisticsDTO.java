package com.resource.model.dto;

import lombok.Data;

@Data
public class ApplicationStatisticsDTO {
    private Long totalResources;           // 总资源数
    private Long pendingApplications;      // 待处理申请数
    private Long monthlyApplications;      // 本月申请数
    private Double averageProcessingTime;  // 平均处理时间（小时）
} 