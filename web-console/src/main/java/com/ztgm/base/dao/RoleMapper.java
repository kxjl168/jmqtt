/**
 * @(#)RoleMapper.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.dao;

import java.util.List;
import java.util.Map;

import com.ztgm.base.pojo.Role;
import com.ztgm.base.pojo.Manager;

/**
 * 
 * RoleMapper.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月14日
* @revision zj 2018年11月14日
* @since 1.0.1
 */
public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    
    /**
     * 根据角色查询管理员，判断是否可以禁用角色
     * @param roleId
     * @return
     * @author zj
     * @date 2018年7月30日
     */
    List<Manager> selectManagerByRole(String roleId);
    
    
    /**
     * 条件查询
     * @param permission
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    List<Role> selectByName(String  name);
    
    /**
     * 条件查询
     * @param permission
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    List<Role> selectRoleByManager(Manager Manager);
    
    /**
     * 条件查询
     * @param permission
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    List<Role> selectRoleList(Role record);
    
    
    /**
     * 查询用户所属角色
     * @param ManagerId
     * @return
     */
    List<Map> selectRoleByManagerId(String ManagerId);


    /**
     * 根据条件查询角色列表
     * @param conditions 条件
     * @return 角色列表
     */
    List<Map> selectRoleByConditions(Map conditions);
}