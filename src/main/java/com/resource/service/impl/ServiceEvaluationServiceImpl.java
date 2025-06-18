package com.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.resource.mapper.ServiceEvaluationMapper;
import com.resource.model.dto.ServiceEvaluationDTO;
import com.resource.model.entity.ServiceEvaluation;
import com.resource.service.ServiceEvaluationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceEvaluationServiceImpl implements ServiceEvaluationService {

    @Autowired
    private ServiceEvaluationMapper evaluationMapper;

    @Override
    public Page<ServiceEvaluation> listEvaluations(Integer pageNum, Integer pageSize) {
        Page<ServiceEvaluation> page = new Page<>(pageNum, pageSize);
        return evaluationMapper.selectPage(page, new LambdaQueryWrapper<>());
    }

    @Override
    public ServiceEvaluation getEvaluationById(Long id) {
        return evaluationMapper.selectById(id);
    }

    @Override
    @Transactional
    public ServiceEvaluation createEvaluation(ServiceEvaluationDTO evaluationDTO) {
        if (evaluationDTO.getEmployeeId() == null) {
            throw new RuntimeException("员工ID不能为空");
        }
        
        ServiceEvaluation evaluation = new ServiceEvaluation();
        BeanUtils.copyProperties(evaluationDTO, evaluation);
        
        // 确保employeeId被正确设置
        evaluation.setEmployeeId(evaluationDTO.getEmployeeId());
        
        // 打印日志以便调试
        System.out.println("Creating evaluation with employeeId: " + evaluation.getEmployeeId());
        
        evaluationMapper.insert(evaluation);
        return evaluation;
    }

    @Override
    @Transactional
    public ServiceEvaluation updateEvaluation(Long id, ServiceEvaluationDTO evaluationDTO) {
        ServiceEvaluation evaluation = evaluationMapper.selectById(id);
        if (evaluation == null) {
            throw new RuntimeException("评价不存在");
        }
        
        BeanUtils.copyProperties(evaluationDTO, evaluation);
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }

    @Override
    @Transactional
    public void deleteEvaluation(Long id) {
        evaluationMapper.deleteById(id);
    }
} 