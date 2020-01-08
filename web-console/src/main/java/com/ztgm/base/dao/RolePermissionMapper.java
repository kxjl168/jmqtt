/**
 * @(#)RolePermissionMapper.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.dao;

import com.ztgm.base.pojo.Role;
import com.ztgm.base.pojo.RolePermission;

/**
 * 角色权限
 * RolePermissionMapper.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月14日
* @revision zj 2018年11月14日
* @since 1.0.1
 */
public interface RolePermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
    
    /***
     * 删除角色的所有权限
     * @param role
     * @author zj
     * @date 2018年5月10日
     */
   int  deleteRolePerssion(Role role);
}