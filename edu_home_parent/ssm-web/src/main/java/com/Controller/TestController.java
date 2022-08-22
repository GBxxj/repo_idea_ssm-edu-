package com.Controller;


import com.domain.Test;
import com.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //@Controller+@ResponsBody(把return结果转成json响应到页面)
@RequestMapping("/test")
public class TestController {

    /*调用service方法*/
    @Autowired
    private TestService testService;

    @RequestMapping("findAllTest")
    public List<Test> findAllTest(){
        List<Test> all= testService.findAllTest();
        return all;
    }
}
