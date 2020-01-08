/**
 * @(#)RolePermission.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.pojo;

import java.io.Serializable;

/**
 * 
 * RolePermission.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月14日
* @revision zj 2018年11月14日
* @since 1.0.1
 */
public class RolePermission  implements Serializable {
    private String id;

    private String sysRoleId;

    private String sysPermissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId == null ? null : sysRoleId.trim();
    }

    public String getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(String sysPermissionId) {
        this.sysPermissionId = sysPermissionId == null ? null : sysPermissionId.trim();
    }
}