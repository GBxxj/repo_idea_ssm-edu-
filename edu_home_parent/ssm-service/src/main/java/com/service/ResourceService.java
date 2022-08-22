package com.service;

import com.domain.Resource;
import com.domain.ResourceVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ResourceService {
    /*资源分类以及多条件查询*/
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
