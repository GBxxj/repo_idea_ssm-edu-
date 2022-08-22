package com.Controller;

import com.domain.*;
import com.service.MenuService;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /*
     *查询所有角色，同时根据条件进行查询
     * */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true,200,"查询所有角色成功",allRole);
    }
    /*
    * 查询所有父子菜单信息
    * */
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        //查询所有顶级菜单及其子集菜单信息，所有顶级菜单的值为-1
     /*1.菜类（白菜、黄瓜、生菜）
     * 2.肉类（猪肉、牛肉、羊肉）
     * 3.水果（苹果、梨、香蕉）
     * */
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);


        HashMap<String ,Object> map=new HashMap<>();
        map.put("parentMenuList",menuList);

        return new ResponseResult(true,200,"询所有顶级菜单及其子集菜单成功",map);

    }

    /*
     * 根据角色id查询该角色关联的菜单信息id
     * */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"根据角色id查询该角色关联的菜单信息id成功",menuByRoleId);
    }

    /*
     * 为角色分配菜单信息
     * */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"为角色分配菜单信息成功",null);
    }
    /*
     * 删除角色
     * */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除角色成功",null);
    }
}
