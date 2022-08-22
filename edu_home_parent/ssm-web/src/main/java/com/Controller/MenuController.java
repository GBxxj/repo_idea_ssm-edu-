package com.Controller;

import com.domain.Menu;
import com.domain.ResponseResult;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
     * 查询所有菜单信息
     * */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true,200,"查询所有菜单信息成功",allMenu);
    }

    /**
     * 回显菜单信息(包括父子菜单的全部信息)
     * */ @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam int id){


        if(id == -1){
            //添加操作 回显不需要查询 menu信息
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String,Object> map = new HashMap<>(); map.put("menuInfo",null); map.put("parentMenuList",menuList);

            ResponseResult result = new ResponseResult(true,200,"添加回显成功",map); return result;
        }else{
            //修改操作 回显
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);


            Map<String,Object> map = new HashMap<>(); map.put("menuInfo",menu); map.put("parentMenuList",menuList);

            ResponseResult result = new ResponseResult(true,200,"修改回显成功",map); return result;
        }


    }
}
