package com.ztgm.base.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ztgm.base.base.PageManager;
import com.ztgm.base.pojo.Manager;
import com.ztgm.base.pojo.Role;



public interface RoleService {

	int deleteByPrimaryKey(String id);

	int insert(Role record);

	
	String insertSelective(Role record, String permissonids);

	Role selectByPrimaryKey(String id);


	String updateByPrimaryKeySelective(Role record, String permissionids);

	int updateByPrimaryKey(Role record);


    /**
     * 条件查询
     * @param permission
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    List<Role> selectByName(String  name);
	
	/**
	 * 更新角色下的权限菜单
	 * 
	 * @param role_id
	 * @param permissionids
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public int updateRolePermissionList(String role_id, String permissionids);

	/**
	 * 构造权限树数据
	 * 
	 * @param role_id
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<String> getRoleTree(String Manager_id);

	/**
	 * update / save
	 * 
	 * @param record
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	// public JSONObject saveOrUpdate(Role record);
	/**
	 * 条件查询
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	PageInfo<Role> selectRoleList(PageManager p,Role role);

	/**
	 * 通过Manager bean对象查询Manager 列表
	 * 
	 * @param ManagerId
	 *            用户id
	 * @return 用户所属角色
	 */
	List<Map> selectRoleByManagerId(String ManagerId);
	
	/**
	 * 根据管理账号查询角色
	 * @param m
	 * @return
	 * @author zj
	 * @date 2019年1月9日
	 */
	List<Role> selectRoleByManager(Manager m);

	/**
	 * 获取所有角色列表
	 * 
	 * @return
	 */
	List<Map> selectRoles();

}
