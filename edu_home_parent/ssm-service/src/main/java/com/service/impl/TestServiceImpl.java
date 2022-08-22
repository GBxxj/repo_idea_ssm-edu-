package com.service.impl;

import com.dao.TestMapper;
import com.domain.Test;
import com.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //生成代理对象存到ioc容器中以便web层调用
public class TestServiceImpl implements TestService {

    /*调用dao层接口方法
    * 注入代理对象*/
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> findAllTest() {
        List<Test> all= testMapper.findAllTest();
        return all;
    }
}
