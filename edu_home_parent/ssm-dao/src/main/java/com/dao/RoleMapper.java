package com.dao;

import com.domain.MenuVo;
import com.domain.Role;
import com.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    /*
     *查询所有角色，同时根据条件进行查询
     * */
    public List<Role> findAllRole(Role role);

    /*
     * 根据角色id查询该角色关联的菜单信息id
     * */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
     *根据roleid清空中间表的关联关系
     * */
    public void deleteRoleContextMenu(Integer rid);
    /*
    * 为角色分配菜单信息
    * */
    public void roleContextMenu(Role_menu_relation role_menu_relation);
    /*
    * 删除角色
    * */
    public void deleteRole(Integer roldid);
}
