package com.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.resource.mapper.ResourceApplicationMapper;
import com.resource.mapper.ResourceMapper;
import com.resource.model.dto.ResourceApplicationDTO;
import com.resource.model.dto.ApplicationStatisticsDTO;
import com.resource.model.entity.Resource;
import com.resource.model.entity.ResourceApplication;
import com.resource.service.ResourceApplicationService;
import com.resource.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ResourceApplicationServiceImpl implements ResourceApplicationService {

    @Autowired
    private ResourceApplicationMapper applicationMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceService resourceService;

    @Override
    public Page<ResourceApplication> listApplications(Integer pageNum, Integer pageSize) {
        Page<ResourceApplication> page = new Page<>(pageNum, pageSize);
        return applicationMapper.selectPage(page, new LambdaQueryWrapper<>());
    }

    @Override
    public ResourceApplication getApplicationById(Long id) {
        return applicationMapper.selectById(id);
    }

    @Override
    @Transactional
    public ResourceApplication createApplication(ResourceApplicationDTO applicationDTO) {
        if (applicationDTO.getEmployeeId() == null) {
            throw new RuntimeException("员工ID不能为空");
        }
        
        ResourceApplication application = new ResourceApplication();
        BeanUtils.copyProperties(applicationDTO, application);
        
        // 确保employeeId被正确设置
        application.setEmployeeId(applicationDTO.getEmployeeId());
        application.setStatus("PENDING");
        application.setSubmitFlag(0);
        application.setApprovalFlag(0);
        
        // 打印日志以便调试
        System.out.println("Creating application with employeeId: " + application.getEmployeeId());
        
        applicationMapper.insert(application);
        return application;
    }

    @Override
    @Transactional
    public ResourceApplication updateApplication(Long id, ResourceApplicationDTO applicationDTO) {
        ResourceApplication application = applicationMapper.selectById(id);
        if (application == null) {
            throw new RuntimeException("申请不存在");
        }
        
        if (application.getSubmitFlag() == 1) {
            throw new RuntimeException("已提交的申请不能修改");
        }
        
        BeanUtils.copyProperties(applicationDTO, application);
        applicationMapper.updateById(application);
        return application;
    }

    @Override
    @Transactional
    public void deleteApplication(Long id) {
        ResourceApplication application = applicationMapper.selectById(id);
        if (application == null) {
            throw new RuntimeException("申请不存在");
        }
        
        if (application.getSubmitFlag() == 1) {
            throw new RuntimeException("已提交的申请不能删除");
        }
        
        applicationMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void submitApplication(Long id) {
        ResourceApplication application = applicationMapper.selectById(id);
        if (application == null) {
            throw new RuntimeException("申请不存在");
        }
        
        application.setSubmitFlag(1);
        applicationMapper.updateById(application);
    }

    @Override
    @Transactional
    public void approveApplication(Long id) {
        ResourceApplication application = applicationMapper.selectById(id);
        if (application == null) {
            throw new RuntimeException("申请不存在");
        }
        
        if (application.getSubmitFlag() != 1) {
            throw new RuntimeException("申请未提交");
        }
        
        // 检查资源数量是否足够
        Resource resource = resourceMapper.selectById(application.getResourceId());
        if (resource == null) {
            throw new RuntimeException("资源不存在");
        }
        
        if (resource.getQuantity() < application.getQuantity()) {
            throw new RuntimeException("资源数量不足");
        }
        
        // 更新资源数量
        resourceService.updateResourceQuantity(resource.getId(), 
            resource.getQuantity() - application.getQuantity());
        
        // 更新申请状态
        application.setStatus("APPROVED");
        application.setApprovalFlag(1);
        applicationMapper.updateById(application);
    }

    @Override
    @Transactional
    public void rejectApplication(Long id) {
        ResourceApplication application = applicationMapper.selectById(id);
        if (application == null) {
            throw new RuntimeException("申请不存在");
        }
        
        if (application.getSubmitFlag() != 1) {
            throw new RuntimeException("申请未提交");
        }
        
        application.setStatus("REJECTED");
        application.setApprovalFlag(1);
        applicationMapper.updateById(application);
    }

    @Override
    public ApplicationStatisticsDTO getApplicationStatistics() {
        ApplicationStatisticsDTO statistics = new ApplicationStatisticsDTO();
        
        // 获取总资源数
        statistics.setTotalResources(resourceMapper.selectCount(null));
        
        // 获取待处理申请数
        LambdaQueryWrapper<ResourceApplication> pendingWrapper = new LambdaQueryWrapper<>();
        pendingWrapper.eq(ResourceApplication::getStatus, "PENDING")
                     .eq(ResourceApplication::getSubmitFlag, 1);
        statistics.setPendingApplications(applicationMapper.selectCount(pendingWrapper));
        
        // 获取本月申请数
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LambdaQueryWrapper<ResourceApplication> monthlyWrapper = new LambdaQueryWrapper<>();
        monthlyWrapper.ge(ResourceApplication::getCreateTime, startOfMonth);
        statistics.setMonthlyApplications(applicationMapper.selectCount(monthlyWrapper));
        
        // 计算平均处理时间
        LambdaQueryWrapper<ResourceApplication> processedWrapper = new LambdaQueryWrapper<>();
        processedWrapper.eq(ResourceApplication::getApprovalFlag, 1);
        List<ResourceApplication> processedApplications = applicationMapper.selectList(processedWrapper);
        
        if (!processedApplications.isEmpty()) {
            double totalHours = processedApplications.stream()
                .mapToDouble(app -> {
                    LocalDateTime createTime = app.getCreateTime();
                    LocalDateTime updateTime = app.getUpdateTime();
                    return ChronoUnit.HOURS.between(createTime, updateTime);
                })
                .sum();
            statistics.setAverageProcessingTime(totalHours / processedApplications.size());
        } else {
            statistics.setAverageProcessingTime(0.0);
        }
        
        return statistics;
    }

    @Override
    public List<ResourceApplication> getRecentApplications() {
        // 获取最近10条申请记录
        LambdaQueryWrapper<ResourceApplication> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ResourceApplication::getCreateTime)
               .last("LIMIT 10");
        return applicationMapper.selectList(wrapper);
    }
} 