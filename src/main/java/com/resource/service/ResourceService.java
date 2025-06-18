package com.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.resource.model.entity.Resource;

public interface ResourceService {
    Page<Resource> listResources(Integer pageNum, Integer pageSize);
    Resource getResourceById(Long id);
    Resource addResource(Resource resource);
    Resource updateResource(Resource resource);
    void deleteResource(Long id);
    void updateResourceQuantity(Long id, Integer quantity);
} 