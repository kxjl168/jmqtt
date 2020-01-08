/**
 * @(#)Role.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.pojo;

import java.io.Serializable;

/**
 * 
 * Role.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月14日
* @revision zj 2018年11月14日
* @since 1.0.1
 */
public class Role  implements Serializable{
    private String id;

    private String name;

    private String available;

    public String getId() {
        return id;
    }

    public Role() {
    	
    }
    public Role(String id,String name,String av)
    {
    	this.id=id;
    	this.name=name;
    	this.available=av;
    }
    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }
}