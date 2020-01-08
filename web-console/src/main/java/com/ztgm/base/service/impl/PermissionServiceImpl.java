package com.ztgm.base.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztgm.base.base.PageManager;
import com.ztgm.base.dao.PermissionMapper;
import com.ztgm.base.dao.RoleMapper;
import com.ztgm.base.pojo.Manager;
import com.ztgm.base.pojo.MenuPermission;
import com.ztgm.base.pojo.Role;
import com.ztgm.base.service.ManagerService;
import com.ztgm.base.service.PermissionService;
import com.ztgm.base.service.RoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	RoleService roleService;

	@Autowired
	ManagerService mService;
	
	@Autowired
	private RoleMapper roleDao;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {

		MenuPermission p = permissionMapper.selectByPrimaryKey(id);
		if (p.getType().equals("1")) {
			// 级联删除全部子菜单
			MenuPermission query = new MenuPermission();
			query.setParentid(p.getId());
			List<MenuPermission> childs = permissionMapper.selectPermissionList(query);
			for (int i = 0; i < childs.size(); i++) {
				permissionMapper.deleteByPrimaryKey(childs.get(i).getId());
			}

		}

		return permissionMapper.deleteByPrimaryKey(id);
	}

	public int insert(MenuPermission record) {
		return permissionMapper.insert(record);
	}

	public String insertSelective(MenuPermission record) {

		JSONObject rtn = new JSONObject();

		try {

			permissionMapper.insertSelective(record);

			rtn.put("result", true);

			return rtn.toString();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}

			return rtn.toString();
		}

	}

	public String saveOrUpdate(MenuPermission record) {
		if (null == record.getId() || "".equals(record.getId()))
			return insertSelective(record);
		else
			return updateByPrimaryKeySelective(record);
	}

	public MenuPermission selectByPrimaryKey(String id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 构造权限树数据 3级菜单/含按钮
	 * 
	 * @param role_id
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<String> getPermissionTreeThree(String role_id) {
		List<String> lstTree = new ArrayList<String>();

		try {

			lstTree = buildPTree(role_id, 3, true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstTree;
	}

	/**
	 * 构造权限树
	 * 
	 * @param level
	 * @return
	 * @author zj
	 * @date 2018年5月31日
	 */
	private List<String> buildPTree(String role_id, int level, boolean isopen) {
		List<String> lstTree = new ArrayList<String>();

		try {

			Role bean = roleDao.selectByPrimaryKey(role_id);

			List<MenuPermission> selectMenus = getRolePermissionList(bean);

			// 一级菜单
			MenuPermission query = new MenuPermission();
			query.setType("1");
			query.setAvailable("1");
			List<MenuPermission> menus = selectPermissionList(null, query).getList();

			String rootid = "0";
			for (MenuPermission menu : menus) {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append("{");
				sBuffer.append("id:\"" + menu.getId() + "\",");
				sBuffer.append("pId:\"" + rootid + "\",");

				sBuffer.append("open:" + (isopen ? "true" : "false") + ",");// 根节点打开

				if (selectMenus != null)
					for (int i = 0; i < selectMenus.size(); i++) {
						if (selectMenus.get(i).getId().equals(menu.getId())) {
							sBuffer.append("checked:true,");// 选中
							break;
						}
					}

				sBuffer.append("name:\"" + menu.getName() + "\",");

				sBuffer.append("remark:\"" + "" + "\"");
				// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
				// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

				sBuffer.append("}");
				lstTree.add(sBuffer.toString());

			}

			for (int i = 0; i < menus.size(); i++) {
				MenuPermission query2 = new MenuPermission();
				query2.setParentid(menus.get(i).getId());
				query2.setAvailable("1");
				List<MenuPermission> all_menus = selectPermissionList(null, query2).getList();

				for (MenuPermission menu : all_menus) {
					StringBuffer sBuffer = new StringBuffer();
					sBuffer.append("{");
					sBuffer.append("id:\"" + menu.getId() + "\",");
					sBuffer.append("pId:\"" + menus.get(i).getId() + "\",");
					sBuffer.append("open:" + (isopen ? "true" : "false") + ",");// 根节点打开

					sBuffer.append("name:\"" + menu.getName() + "\",");

					if (selectMenus != null)
						for (int j = 0; j < selectMenus.size(); j++) {
							if (selectMenus.get(j).getId().equals(menu.getId())) {
								sBuffer.append("checked:true,");// 选中
								break;
							}
						}

					sBuffer.append("remark:\"" + "" + "\"");
					// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
					// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

					sBuffer.append("}");
					lstTree.add(sBuffer.toString());

				}
			}

			if (level >= 3) {
				// 三级菜单按钮

				MenuPermission query2 = new MenuPermission();
				query2.setType("3");
				query2.setAvailable("1");
				List<MenuPermission> all_menus = selectPermissionList(null, query2).getList();

				for (MenuPermission menu : all_menus) {
					StringBuffer sBuffer = new StringBuffer();
					sBuffer.append("{");
					sBuffer.append("id:\"" + menu.getId() + "\",");
					sBuffer.append("pId:\"" + menu.getParentid() + "\",");
					sBuffer.append("open:" + (isopen ? "true" : "false") + ",");// 根节点打开

					sBuffer.append("name:\"" + menu.getName() + "\",");

					if (selectMenus != null)
						for (int j = 0; j < selectMenus.size(); j++) {
							if (selectMenus.get(j).getId().equals(menu.getId())) {
								sBuffer.append("checked:true,");// 选中
								break;
							}
						}

					sBuffer.append("remark:\"" + "" + "\"");
					// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
					// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

					sBuffer.append("}");
					lstTree.add(sBuffer.toString());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstTree;
	}

	/**
	 * 构造权限树数据
	 * 
	 * @param role_id
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<String> getPermissionTreeSecond(String role_id) {
		List<String> lstTree = new ArrayList<String>();

		try {

			lstTree = buildPTree(role_id, 2, false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstTree;

	}

	public String updateByPrimaryKeySelective(MenuPermission record) {

		JSONObject rtn = new JSONObject();

		try {

			if (null == record || null == record.getId()) {
				rtn.put("result", false);
				rtn.put("message", "id为空");
				return rtn.toString();
			}
			permissionMapper.updateByPrimaryKeySelective(record);

			rtn.put("result", true);

			return rtn.toString();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}

			return rtn.toString();
		}

	}

	public int updateByPrimaryKey(MenuPermission record) {
		return permissionMapper.updateByPrimaryKey(record);
	}

	/**
	 * 获取角色的所有权限
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<MenuPermission> getRolePermissionList(Role role) {
		return permissionMapper.getRolePermissionList(role);
	}

	/**
	 * 条件查询
	 * 
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public PageInfo<MenuPermission> selectPermissionList(PageManager p, MenuPermission permission) {
		Page page = new Page();
		if (p != null)
			page = PageHelper.startPage(p.getPage(), p.getPageRows());
		List<MenuPermission> list = permissionMapper.selectPermissionList(permission);

		PageInfo<MenuPermission> rst = new PageInfo<>(list);
		rst.setTotal(page.getTotal());
		rst.setPageNum(page.getPageNum());
		rst.setPageSize(page.getPageSize());
		return rst;
	}

	@Override
	public List<MenuPermission> selectPermissionsByRoleId(String roleId) {
		return permissionMapper.selectPermissionsByRoleId(roleId);
	}

	@Override
	public List<MenuPermission> selectPermissionsByManagerId(String mangerId) {
		return permissionMapper.selectPermissionsByManagerId(mangerId);
	}

	@Override
	public List<Map> selectPermissions() {
		return permissionMapper.selectPermissions();
	}

	public static final String permissionType_1 = "1";
	public static final String permissionType_2 = "2";

	@Override
	public void refreshMenu() {

		Subject subject = SecurityUtils.getSubject();

		Map principal = (Map) subject.getPrincipal();

		Manager user = (Manager) principal.get("user");

		List<MenuPermission> allUserPermissions = new ArrayList<>();
		List<MenuPermission> userPermissions;

		List<MenuPermission> userMenus = new ArrayList<>();

		// 查询用户角色和权限
		List<Map> roles = roleService.selectRoleByManagerId(user.getId());

		List<MenuPermission> rolePermissions = selectPermissionsByManagerId(user.getId());
		if (null != roles) {

			if (null != rolePermissions) {
				for (MenuPermission rr : rolePermissions) {

					if (!allUserPermissions.contains(rr))
						allUserPermissions.add(rr);
				}

				for (MenuPermission rr : rolePermissions) {

					if (permissionType_1.equals(rr.getType())) {
						userPermissions = new ArrayList();
						String menuid = String.valueOf(rr.getId());
						for (MenuPermission r2 : rolePermissions) {
							String pid = String.valueOf(r2.getParentid());
							if (pid.equals(menuid))
								userPermissions.add(r2);
						}

						rr.setPermissions(userPermissions);

						userMenus.add(rr);

					}

				}

			}
		}
		
		
		//重新获取头像等
		user=mService.selectByPrimaryKey(user.getId());

		

		principal.put("user", user);
		principal.put("userId", user.getId());
		principal.put("userName",
				user.getNickname() == null || "".equals(user.getNickname()) ? user.getTelephone() : user.getNickname());
		principal.put("userMenus", userMenus);
		principal.put("userPermissions", allUserPermissions);
		principal.put("roles", roles);

		//更新
		PrincipalCollection principalCollection = subject.getPrincipals();
		String realmName = principalCollection.getRealmNames().iterator().next();
		PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(principal, realmName);
		// 重新加载Principal
		subject.runAs(newPrincipalCollection);

	}

}
