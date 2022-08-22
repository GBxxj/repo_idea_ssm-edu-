package com.dao;

import com.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*
    * 查询所有父子菜单信息
    * */
    public List<Menu> findSubMenuListByPid(int pid);
    /*
    * 查询所有菜单信息
    * */
    public List<Menu> findAllMenu();

    Menu findMenuById(int id);
}
