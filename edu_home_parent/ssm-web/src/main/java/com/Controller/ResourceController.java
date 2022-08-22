package com.Controller;

import com.domain.Resource;
import com.domain.ResourceVo;
import com.domain.ResponseResult;
import com.github.pagehelper.PageInfo;
import com.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;


    /*资源分类以及多条件查询*/
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVo resourceVo){
        PageInfo<Resource> allResourceByPage = resourceService.findAllResourceByPage(resourceVo);

        return new ResponseResult(true,200,"资源分类以及多条件查询成功",allResourceByPage);
    }
}
