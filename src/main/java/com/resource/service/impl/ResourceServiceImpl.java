package com.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.resource.mapper.ResourceMapper;
import com.resource.model.entity.Resource;
import com.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Page<Resource> listResources(Integer pageNum, Integer pageSize) {
        Page<Resource> page = new Page<>(pageNum, pageSize);
        return resourceMapper.selectPage(page, new LambdaQueryWrapper<>());
    }

    @Override
    public Resource getResourceById(Long id) {
        return resourceMapper.selectById(id);
    }

    @Override
    @Transactional
    public Resource addResource(Resource resource) {
        resourceMapper.insert(resource);
        return resource;
    }

    @Override
    @Transactional
    public Resource updateResource(Resource resource) {
        resourceMapper.updateById(resource);
        return resource;
    }

    @Override
    @Transactional
    public void deleteResource(Long id) {
        resourceMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void updateResourceQuantity(Long id, Integer quantity) {
        Resource resource = resourceMapper.selectById(id);
        if (resource == null) {
            throw new RuntimeException("资源不存在");
        }
        resource.setQuantity(quantity);
        resourceMapper.updateById(resource);
    }
} 