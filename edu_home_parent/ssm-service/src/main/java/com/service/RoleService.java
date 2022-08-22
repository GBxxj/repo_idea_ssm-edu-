package com.service;

import com.domain.Role;
import com.domain.RoleMenuVo;
import com.domain.Role_menu_relation;

import java.util.List;

public interface RoleService {
    /*
     *查询所有角色，同时根据条件进行查询
     * */
    public List<Role> findAllRole(Role role);
    /*
     * 根据角色id查询该角色关联的菜单信息id
     * */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
     * 为角色分配菜单信息
     * */
    public void roleContextMenu(RoleMenuVo roleMenuVo);
    /*
     * 删除角色
     * */
    public void deleteRole(Integer roldid);
}
