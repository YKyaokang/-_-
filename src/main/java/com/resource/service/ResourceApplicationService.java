package com.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.resource.model.dto.ResourceApplicationDTO;
import com.resource.model.dto.ApplicationStatisticsDTO;
import com.resource.model.entity.ResourceApplication;
import java.util.List;

public interface ResourceApplicationService {
    Page<ResourceApplication> listApplications(Integer pageNum, Integer pageSize);
    ResourceApplication getApplicationById(Long id);
    ResourceApplication createApplication(ResourceApplicationDTO applicationDTO);
    ResourceApplication updateApplication(Long id, ResourceApplicationDTO applicationDTO);
    void deleteApplication(Long id);
    void submitApplication(Long id);
    void approveApplication(Long id);
    void rejectApplication(Long id);
    
    // 获取申请统计数据
    ApplicationStatisticsDTO getApplicationStatistics();
    
    // 获取最近的申请列表
    List<ResourceApplication> getRecentApplications();
} 