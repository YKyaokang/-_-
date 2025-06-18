package com.resource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.resource.common.Result;
import com.resource.model.dto.ServiceEvaluationDTO;
import com.resource.model.entity.ServiceEvaluation;
import com.resource.service.ServiceEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluation")
public class ServiceEvaluationController {

    @Autowired
    private ServiceEvaluationService evaluationService;

    @GetMapping("/list")
    public Result<Page<ServiceEvaluation>> listEvaluations(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            Page<ServiceEvaluation> page = evaluationService.listEvaluations(pageNum, pageSize);
            return Result.success(page);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("get/{id}")
    public Result<ServiceEvaluation> getEvaluationById(@PathVariable Long id) {
        try {
            ServiceEvaluation evaluation = evaluationService.getEvaluationById(id);
            return Result.success(evaluation);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("add")
    public Result<ServiceEvaluation> createEvaluation(@RequestBody ServiceEvaluationDTO evaluationDTO) {
        try {
            ServiceEvaluation evaluation = evaluationService.createEvaluation(evaluationDTO);
            return Result.success(evaluation);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Result<ServiceEvaluation> updateEvaluation(
            @PathVariable Long id,
            @RequestBody ServiceEvaluationDTO evaluationDTO) {
        try {
            ServiceEvaluation evaluation = evaluationService.updateEvaluation(id, evaluationDTO);
            return Result.success(evaluation);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public Result<Void> deleteEvaluation(@PathVariable Long id) {
        try {
            evaluationService.deleteEvaluation(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 