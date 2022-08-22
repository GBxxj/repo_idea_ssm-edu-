package com.dao;

import com.domain.Resource;
import com.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {

    /*资源分类以及多条件查询*/
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
