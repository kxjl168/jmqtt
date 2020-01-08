/**
 * @(#)MenuPermission.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ztgm.base.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单/权限
 * MenuPermission.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月14日
* @revision zj 2018年11月14日
* @since 1.0.1
 */
@SuppressWarnings("serial")
public class MenuPermission implements Serializable {
 
	private String id;

    private String name;

    private String type;

    private String url;

    private String percode;

    private String parentid;

    private String parentids;

    private String sortstring;

    private String available;
    
    private String icon;
    
 
    //二级菜单
    private List<MenuPermission> permissions=new ArrayList<>();
    
    //query
    private String parentname;
    
    

    public String getId() {
        return id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode == null ? null : percode.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getParentids() {
        return parentids;
    }

    public void setParentids(String parentids) {
        this.parentids = parentids == null ? null : parentids.trim();
    }

    public String getSortstring() {
        return sortstring;
    }

    public void setSortstring(String sortstring) {
        this.sortstring = sortstring == null ? null : sortstring.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }

	public List<MenuPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<MenuPermission> permissions) {
		this.permissions = permissions;
	}
/*
	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}*/

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

/*	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}*/

}