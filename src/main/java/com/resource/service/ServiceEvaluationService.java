package com.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.resource.model.dto.ServiceEvaluationDTO;
import com.resource.model.entity.ServiceEvaluation;

public interface ServiceEvaluationService {
    Page<ServiceEvaluation> listEvaluations(Integer pageNum, Integer pageSize);
    ServiceEvaluation getEvaluationById(Long id);
    ServiceEvaluation createEvaluation(ServiceEvaluationDTO evaluationDTO);
    ServiceEvaluation updateEvaluation(Long id, ServiceEvaluationDTO evaluationDTO);
    void deleteEvaluation(Long id);
} 