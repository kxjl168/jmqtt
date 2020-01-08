/**
 * @(#)PermissionMapper.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztgm.base.base.PageManager;
import com.ztgm.base.pojo.MenuPermission;
import com.ztgm.base.pojo.Role;

/**
 * 
 * PermissionMapper.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月14日
* @revision zj 2018年11月14日
* @since 1.0.1
 */
public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(MenuPermission record);

    int insertSelective(MenuPermission record);

    MenuPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MenuPermission record);

    int updateByPrimaryKey(MenuPermission record);
    
    /**
     * 条件查询
     * @param permission
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    
    List<MenuPermission> selectPermissionList(MenuPermission permission);
    
    /**
     * 获取角色拥有的权限
     * @param role
     * @return
     * @author zj
     * @date 2019年1月9日
     */
    public List<MenuPermission> getRolePermissionList(Role role);
    /**
     * 根据角色id查询角色对应权限列表
     * @param roleId
     * @return
     */
    List<MenuPermission> selectPermissionsByRoleId(String roleId);
    
    /**
     * 根据角色id查询角色对应权限列表
     * @param roleId
     * @return
     */
    List<MenuPermission> selectPermissionsByManagerId(String managerId);


    /**
     * 查询系统现有所有功能列表
     * @return
     */
    List<Map> selectPermissions();
}