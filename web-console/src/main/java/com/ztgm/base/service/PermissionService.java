package com.ztgm.base.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ztgm.base.base.PageManager;
import com.ztgm.base.pojo.MenuPermission;
import com.ztgm.base.pojo.Role;

public interface PermissionService {

	/**
	 * 修改角色菜单后，刷新信息
	 * 
	 * @author zj
	 * @date 2019年1月9日
	 */
	void refreshMenu();
	
	int deleteByPrimaryKey(String id);

	int insert(MenuPermission record);

	String insertSelective(MenuPermission record);

	MenuPermission selectByPrimaryKey(String id);

	String updateByPrimaryKeySelective(MenuPermission record);

	int updateByPrimaryKey(MenuPermission record);

	
	/**
	 * update / save
	 * @param record
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public String saveOrUpdate(MenuPermission record);
	/**
	 * 条件查询
	 * 
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	PageInfo<MenuPermission> selectPermissionList(PageManager p,MenuPermission permission);
	

	/**
	 * 条件查询-二级权限
	 * 
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<String> getPermissionTreeSecond(String role_id);
	
	/**
	 * 三级权限菜单树 -含按钮
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月31日
	 */
	List<String> getPermissionTreeThree(String role_id);

	
	
	/**
	 * 获取角色的所有权限
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	List<MenuPermission> getRolePermissionList(Role role);
	
	
	/**
	 * 通过roleId 查询角色对应权限
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	List<MenuPermission> selectPermissionsByRoleId(String roleId);
	
	
	/**
	 * 通过mangerId 查询角色对应权限
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	List<MenuPermission> selectPermissionsByManagerId(String managerId);

	/**
	 * 查询所有可用功能权限
	 * 
	 * @return
	 */
	List<Map> selectPermissions();

}
