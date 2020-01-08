/*
 * @(#)SysPlatController.java
 * @author: zhangJ
 * @Date: 2019-01-11 15:20:49
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.controller.WebController;



import com.github.pagehelper.Page;
import com.ztgm.base.aopAspect.FunLogType;
import com.ztgm.base.aopAspect.ManagerActionLog;
import com.ztgm.base.base.PageCondition;
import com.ztgm.base.util.Message;
import com.ztgm.base.util.PageUtil;

import com.ztgm.base.pojo.SysPlat;
import com.ztgm.base.service.SysPlatService;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 平台信息表管理 SysPlatController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-01-11 15:20:49
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/sysplat")
public class SysPlatController {
	
	@Autowired
	private SysPlatService sysplatService;


	@RequestMapping("/manager")
	public String manager(Model model) {

		return "/backend/page/sysplat/index.ftl";
	}

	@RequestMapping("/sysplatList")
	//@ManagerActionLog(operateDescribe="查询平台信息表",operateFuncType=FunLogType.Query,operateModelClassName=SysPlatMapper.class)
	@ResponseBody
	public String sysplatList( SysPlat item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<SysPlat> sysplats = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		sysplats = sysplatService.selectSysPlatList(item);

		try {
			rst = PageUtil.packageTableData(page, sysplats);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除平台信息表",operateFuncType=FunLogType.Del,operateModelClassName=SysPlatMapper.class)
	@ResponseBody
	public Message delete( SysPlat item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = sysplatService.deleteSysPlat(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		SysPlat sysplats = sysplatService.selectSysPlatById(id);
		return JSONObject.fromObject(sysplats).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param sysplat
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改平台信息表",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=SysPlatMapper.class)
	@ResponseBody
	public String saveOrUpdate(SysPlat sysplat) {

		JSONObject jsonObject = null;
		try {
			if (null == sysplat.getId() || "".equals(sysplat.getId())) {
				
				jsonObject = sysplatService.saveSysPlat(sysplat);

			} else {
				jsonObject = sysplatService.updateSysPlat(sysplat);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectsysplat")
    @ResponseBody
    public List<SysPlat> selectsysplat( SysPlat item) {
        return sysplatService.selectSysPlatList(item);
    }


}