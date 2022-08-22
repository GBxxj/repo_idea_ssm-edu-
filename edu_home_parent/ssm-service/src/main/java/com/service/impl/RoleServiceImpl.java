package com.service.impl;

import com.dao.RoleMapper;
import com.domain.Role;
import com.domain.RoleMenuVo;
import com.domain.Role_menu_relation;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    /*调用dao层接口方法
     * 注入代理对象*/
    @Autowired
    private RoleMapper roleMapper;



    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //先清空中间表的关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());


            //封装数据
            Date date=new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");


            roleMapper.roleContextMenu(role_menu_relation);
        }





    }

    @Override
    public void deleteRole(Integer roldid) {
        //根据roleid先删除其关联的菜单信息
        roleMapper.deleteRoleContextMenu(roldid);
        //删除角色信息
        roleMapper.deleteRole(roldid);
    }
}
