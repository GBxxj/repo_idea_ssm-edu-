package com.service.impl;

import com.dao.UserMapper;
import com.domain.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.UserService;
import com.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    /*调用dao方法，注入*/
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        //实现分页效果
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());

        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        //封装数据
        User user=new User();
        user.setId(id);
        user.setStatus(status);

        userMapper.updateUserStatus(user);

    }

    @Override
    public User login(User user) throws Exception {
        // user:前台登录的用户信息
        // uer2:数据库中包含密文密码的用户信息
        User user2= userMapper.login(user);

        if (user2!=null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())){
            //查询出来不为空且密码正确
            return user2;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //根据用户id清空中间表关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        //重新分配角色
        for (Integer roleid : userVo.getRoleIdList()) {

            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId()); user_role_relation.setRoleId(roleid);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);

        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        //1.根据用户id查询关联的角色信息 (多个角色)
        List<Role> roleList = userMapper.findUserRelationRoleById(userid);

        //2.获取角色信息中的角色id,根据角色id,查詢角色所拥有的顶级菜单（parent_id=-1的为顶级菜单）
        ArrayList<Integer> roleIds=new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //3.根据顶级菜单查询其子集菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubmenuList(subMenu);
        }

        //4.获取用户拥有的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //5.封装数据并返回
        Map<String,Object> map=new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }
}
