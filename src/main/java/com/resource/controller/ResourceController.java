package com.resource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.resource.common.Result;
import com.resource.model.entity.Resource;
import com.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/list")
    public Result<Page<Resource>> listResources(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            Page<Resource> page = resourceService.listResources(pageNum, pageSize);
            return Result.success(page);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("get/{id}")
    public Result<Resource> getResourceById(@PathVariable Long id) {
        try {
            Resource resource = resourceService.getResourceById(id);
            return Result.success(resource);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("add")
    public Result<Resource> addResource(@RequestBody Resource resource) {
        try {
            Resource newResource = resourceService.addResource(resource);
            return Result.success(newResource);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Result<Resource> updateResource(@PathVariable Long id, @RequestBody Resource resource) {
        try {
            resource.setId(id);
            Resource updatedResource = resourceService.updateResource(resource);
            return Result.success(updatedResource);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public Result<Void> deleteResource(@PathVariable Long id) {
        try {
            resourceService.deleteResource(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/quantity/{id}")
    public Result<Void> updateResourceQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        try {
            resourceService.updateResourceQuantity(id, quantity);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 