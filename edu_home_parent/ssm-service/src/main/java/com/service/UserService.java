package com.service;

import com.domain.ResponseResult;
import com.domain.Role;
import com.domain.User;
import com.domain.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    /*
     * 用户分页多条件查询
     * */
    public PageInfo findAllUserByPage(UserVo userVo);
    /*
     * 更改用户状态信息
     * */
    public void updateUserStatus(int id,String status);
    /*
     * 用户登录（根据用户名查询加密密码进行校对）
     * */
    public User login(User user) throws Exception;
    /*
     * 根据用户id查询关联的角色信息
     * */
    public List<Role> findUserRelationRoleById(Integer id);
    /*
    * 分配用户角色
    * */
    public void userContextRole(UserVo userVo);
    /*
    * 获取用户权限，进行菜单动态展示
    * */
    public ResponseResult getUserPermissions(Integer userid);
}

