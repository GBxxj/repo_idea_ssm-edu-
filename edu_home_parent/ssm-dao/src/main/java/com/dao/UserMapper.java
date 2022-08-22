package com.dao;

import com.domain.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {
        /*
         * 用户分页多条件查询
         * */
        public List<User> findAllUserByPage(UserVo userVo);
        /*
        * 更改用户状态信息
        * */
        public void updateUserStatus(User user);
        /*
        * 用户登录（根据用户名查询加密密码进行校对）
        * */
        public User login(User user);
        /*
        * 根据用户Id清空中间表
        * */
        public void deleteUserContextRole(Integer userId);
        /*
        * 分配角色
        * */
        public void userContextRole(User_Role_relation user_role_relation);
        /*
         * 1.根据用户id查询关联的角色信息 (多个角色)
         * */
        public List<Role> findUserRelationRoleById(Integer id);
        /*
        * 2.获取角色信息中的角色id，根据角色id,查詢角色所拥有的顶级菜单（parent_id=-1的为顶级菜单）
        * */
        public List<Menu> findParentMenuByRoleId(List<Integer> ids);
        /*
        * 3.根据顶级菜单查询其子集菜单
        * */
        public List<Menu> findSubMenuByPid(Integer pid);
        /*
        * 4.获取用户拥有的资源信息
        * */
        public List<Resource> findResourceByRoleId(List<Integer> ids);

}
