package com.ztgm.base.service;

import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ztgm.base.base.PageManager;
import com.ztgm.base.pojo.Manager;
import com.ztgm.base.pojo.OAuthUserInfo;

public interface ManagerService {

	/**
	 * 根据oauthid获取
	 * @param user
	 * @return
	 * @author zj
	 * @date 2019年2月21日
	 */
	Manager selectManagerByOauthId(Manager user);

	/**
	 * 根据oauth返回注册新用户
	 * @param oinfo
	 * @return
	 * @author zj
	 * @date 2018年11月20日
	 */
	Manager registerUser(OAuthUserInfo oinfo);
	
	/**
	 * 根据oauth信息 更新用户信息
	 * @param oinfo
	 * @return
	 * @author zj
	 * @date 2018年11月20日
	 */
	Manager updateUser(OAuthUserInfo oinfo);
	
	
	
	/**
	 * 登陆
	 * 
	 * @param user
	 * @return
	 * @author zj
	 * @date 2018年11月9日
	 */
	Manager getLoginUser(Manager user);

	int deleteByPrimaryKey(String id);

	int insert(Manager record);

	int insertSelective(Manager record);

	Manager selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Manager record);

	int updateByPrimaryKeyWithBLOBs(Manager record);

	int updateByPrimaryKey(Manager record);

	/**
	 * 通过Manager bean对象查询Manager 列表
	 * 
	 * @param Manager
	 * @return
	 */
	PageInfo<Manager> selectManagerByManager(PageManager p, Manager Manager);

	/**
	 * 通过Manager bean对象查询Manager 列表
	 * 
	 * @param Manager
	 * @return
	 */
	Map initSuperAdmin(Manager Manager);

	/**
	 * 获取系统中现有的管理员用户列表
	 * 
	 * @return 现有管理员用户列表
	 */
	List<Manager> getAdminManagers();

	/**
	 * 新增用户
	 */
	String saveManager(Manager Manager);

	/**
	 * 新增用户
	 */
	String saveManager(Manager Manager, String roleids);

	/**
	 * 更新管理员权限信息
	 * 
	 * @param Manager
	 * @param roleids
	 * @throws Exception
	 * @author zj
	 * @date 2019年1月9日
	 */
	void updateManagerRole(Manager Manager, String roleids) throws Exception;

	/**
	 * 更新管理员基本信息及权限信息
	 * 
	 * @param Manager
	 * @param roleids
	 * @return
	 * @author zj
	 * @date 2019年1月9日
	 */
	String updateManager(Manager Manager, String roleids);

	/**
	 * 通过Manager bean对象查询Manager 列表
	 * 
	 * @param conditions
	 * @return
	 */
	List<Map> selectManagerByManager(Map conditions);

	List<Map> selectManagerList(Page p);

	int deleteManager(String uid);

}
