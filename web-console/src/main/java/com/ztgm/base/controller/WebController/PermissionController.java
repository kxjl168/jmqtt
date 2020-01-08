package com.ztgm.base.controller.WebController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.ztgm.base.base.BaseController;
import com.ztgm.base.base.PageManager;
import com.ztgm.base.pojo.MenuPermission;
import com.ztgm.base.service.PermissionService;
import com.ztgm.base.service.RoleService;
import com.ztgm.base.util.Message;
import com.ztgm.base.util.TemplateFileUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/privilege/permission")
public class PermissionController extends BaseController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	/**
	 * 刷新并重新加载菜单
	 * 
	 * @param request
	 * @return
	 * @author zj
	 * @date 2019年1月9日
	 */
	@RequestMapping("/refreshMenu")
	@ResponseBody
	public Message refreshMenu(HttpServletRequest request) {
		Message msg = new Message();
		msg.setBol(false);
		msg.setMessage("fail");
		try {
			permissionService.refreshMenu();

			
			
			
			msg.setBol(true);
			msg.setMessage("ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setBol(false);
			msg.setMessage(e.getMessage());
		}
		return msg;
	}

	/**
	 * 权限菜单管理
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	@RequestMapping("/index")
	public String index(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {

		// Map principal = (Map) request.getAttribute("principal");

		return "/backend/privilege/permission/index.ftl";
	}

	/**
	 * bootstrap tables jquery查询数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String list(@RequestParam Map<String, String> params, HttpServletRequest request,
			HttpServletResponse response) {

		String name = request.getParameter("name");
		String pid = request.getParameter("pid");
		String type = request.getParameter("type");
		// pageNo
		String offset = request.getParameter("offset");

		// pageSize;
		String ps = request.getParameter("pageSize");

		int pageNo = 0;
		int pageSize = 10;
		int ofst = 0;
		try {
			if (offset != null) {
				ofst = Integer.parseInt(offset);

			}

			if (null != ps) {
				pageSize = Integer.parseInt(ps);
			}

			pageNo = (int) Math.ceil(((double) (ofst + 1)) / ((double) pageSize));

		} catch (NumberFormatException e) {

		}

		// Page page = PageHelper.startPage(pageNo, pageSize);
		PageManager p = new PageManager(pageNo, pageSize);

		/*
		 * Subject subject = SecurityUtils.getSubject(); String userId =
		 * UserIDUtil.getUserID(subject, response);
		 */

		MenuPermission q = new MenuPermission();
		q.setName(name);
		q.setParentid(pid);
		q.setType(type);

		PageInfo<MenuPermission> rst = permissionService.selectPermissionList(p, q);

		Gson gs = new Gson();
		JSONObject data = new JSONObject();

		try {
			JSONArray rows = new JSONArray(gs.toJson(rst.getList()));
			data.put("total", rst.getTotal());
			data.put("rows", rows);
		} catch (Exception e) {
			logger.error("error", e);
		}

		String datastr = data.toString();
		return datastr;// responseData(request, response, datastr);

	}

	@RequestMapping("/load")
	@ResponseBody
	public MenuPermission load(HttpServletRequest request) {
		String id = request.getParameter("id");

		MenuPermission p = permissionService.selectByPrimaryKey(id);

		return p;
	}

	@RequestMapping("/delete")
	@ResponseBody
	// @Transactional(rollbackFor = { Exception.class }, propagation =
	// Propagation.REQUIRES_NEW)
	public Message delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		Message msg = new Message();
		try {
			int result = permissionService.deleteByPrimaryKey(id);
			if (result == 1) {
				msg.setBol(true);
			}
		} catch (Exception e) {
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			msg.setBol(false);
			if (e.getMessage().contains("a foreign key constraint")) {
				msg.setMessage("已关联至角色的权限无法删除");
			}
		}

		return msg;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(MenuPermission permission, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		try {

			MenuPermission ptmp = permissionService.selectByPrimaryKey(permission.getId());
			if (ptmp != null) {

				jsonObject.put("result", false);
				jsonObject.put("message", "已存在相同ID的菜单项");
			} else
				jsonObject = new JSONObject(permissionService.insertSelective(permission));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();

	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(MenuPermission permission, HttpServletRequest request) {
		String jsonObject = "";
		try {
			jsonObject = permissionService.updateByPrimaryKeySelective(permission);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();

	}

	/**
	 * 构造用户树数据 zTree
	 * 
	 * @param orgname
	 *            用户组或者用户名称
	 * @return
	 * @date 2016-3-3
	 * @author zj
	 */
	@RequestMapping(value = { "/getMenuTree" }, method = RequestMethod.POST)
	public @ResponseBody List<String> getMenuTree(@RequestParam("role_id") String role_id) {
		// 获取所有部门信息
		return permissionService.getPermissionTreeThree(role_id);
	}

	/**
	 * 构造用户树数据 zTree -二级查询
	 * 
	 * @param orgname
	 *            用户组或者用户名称
	 * @return
	 * @date 2016-3-3
	 * @author zj
	 */
	@RequestMapping(value = { "/getMenuTreeSecond" }, method = RequestMethod.POST)
	public @ResponseBody List<String> getMenuTreeSecond(@RequestParam("role_id") String role_id) {
		// 获取所有部门信息
		return permissionService.getPermissionTreeSecond(role_id);
	}

}
