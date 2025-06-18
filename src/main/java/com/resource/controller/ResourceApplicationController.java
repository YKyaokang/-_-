package com.resource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.resource.common.Result;
import com.resource.model.dto.ResourceApplicationDTO;
import com.resource.model.dto.ApplicationStatisticsDTO;
import com.resource.model.entity.ResourceApplication;
import com.resource.service.ResourceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/application")
public class ResourceApplicationController {

    @Autowired
    private ResourceApplicationService applicationService;

    /**
     * 获取申请列表
     */
    @GetMapping("/list")
    public Result<Page<ResourceApplication>> listApplications(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            Page<ResourceApplication> page = applicationService.listApplications(pageNum, pageSize);
            return Result.success(page);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 获取单个申请详情
     */
    @GetMapping("get/{id}")
    public Result<ResourceApplication> getApplicationById(@PathVariable Long id) {
        try {
            ResourceApplication application = applicationService.getApplicationById(id);
            return Result.success(application);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 创建资源申请
     */
    @PostMapping("add")
    public Result<ResourceApplication> createApplication(@RequestBody ResourceApplicationDTO applicationDTO) {
        try {
            ResourceApplication application = applicationService.createApplication(applicationDTO);
            return Result.success(application);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 更新资源申请
     */
    @PutMapping("/update/{id}")
    public Result<ResourceApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody ResourceApplicationDTO applicationDTO) {
        try {
            ResourceApplication application = applicationService.updateApplication(id, applicationDTO);
            return Result.success(application);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 删除资源申请
     */
    @DeleteMapping("delete/{id}")
    public Result<Void> deleteApplication(@PathVariable Long id) {
        try {
            applicationService.deleteApplication(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 提交资源申请
     */
    @PostMapping("submit/{id}")
    public Result<Void> submitApplication(@PathVariable Long id) {
        try {
            applicationService.submitApplication(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 审批通过资源申请
     */
    @PostMapping("/approve/{id}")
    public Result<Void> approveApplication(@PathVariable Long id) {
        try {
            applicationService.approveApplication(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 拒绝资源申请
     */
    @PostMapping("/reject/{id}")
    public Result<Void> rejectApplication(@PathVariable Long id) {
        try {
            applicationService.rejectApplication(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 获取申请统计数据
     */
    @GetMapping("/statistics")
    public Result<ApplicationStatisticsDTO> getApplicationStatistics() {
        try {
            ApplicationStatisticsDTO statistics = applicationService.getApplicationStatistics();
            return Result.success(statistics);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 获取最近的申请列表
     */
    @GetMapping("/recent")
    public Result<List<ResourceApplication>> getRecentApplications() {
        try {
            List<ResourceApplication> applications = applicationService.getRecentApplications();
            return Result.success(applications);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 