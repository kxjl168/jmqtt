/**
 * @(#)ManagerRole.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ztgm.base.pojo;

import java.io.Serializable;



/**
 * 后台用户-角色关系
 * ManagerRole.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月14日
* @revision zj 2018年11月14日
* @since 1.0.1
 */
public class ManagerRole implements Serializable {
    private String id;

    private String sysManagerId;

    private String sysRoleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSysManagerId() {
        return sysManagerId;
    }

    public void setSysManagerId(String sysManagerId) {
        this.sysManagerId = sysManagerId == null ? null : sysManagerId.trim();
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId == null ? null : sysRoleId.trim();
    }
}