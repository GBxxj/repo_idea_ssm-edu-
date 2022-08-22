package com.dao;

import com.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {
    /*查询所有资源分类*/
    public List<ResourceCategory> findAllResourceCategory();
}
