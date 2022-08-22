package com.Controller;

import com.domain.ResponseResult;
import com.domain.Role;
import com.domain.User;
import com.domain.UserVo;
import com.github.pagehelper.PageInfo;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /*
     * 用户分页多条件查询
     * */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        return new ResponseResult(true,200,"分页多条件查询成功",pageInfo);
    }

    /*
     * 更改用户状态信息
     * */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id,String status){

        userService.updateUserStatus(id,status);

        return new ResponseResult(true,200,"更改用户状态成功",null);
    }
    /*
     * 用户登录（根据用户名查询加密密码进行校对）
     * */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1= userService.login(user);
        if(user1!=null){
            //保存用户id以及access_token到session中
            HttpSession session=request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            Map<String,Object> map=new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());

            return new ResponseResult(true,200,"登录成功",map);

        }else {
            return new ResponseResult(true,400,"用户名密码错误",null);
        }

    }
    /*
    * 根据用户id查询关联的角色信息
    * */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> list = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"根据用户id查询关联的角色信息成功",list);
    }
    /*
     * 分配角色
     * */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true,200,"分配角色成功",null);
    }

    /*
    * 获取用户信息权限进行菜单动态展现
    * */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request ){

        //1.获取请求头中携带的信息
        String header_token = request.getHeader("Authorization");

        //2.获取session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");

        //3.判断token是否一致
        if(header_token.equals(session_token)){
            //获取存进session的用户id
             Integer user_id = (Integer) request.getSession().getAttribute("user_id");
             //根据用户id进行查询
            ResponseResult responseResult= userService.getUserPermissions(user_id);
            return responseResult;
        }else{
            return new ResponseResult(false,400,"获取菜单信息失败",null);
        }



    }
}
