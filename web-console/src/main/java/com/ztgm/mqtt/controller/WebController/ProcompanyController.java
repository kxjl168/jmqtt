/*
 * @(#)ProcompanyController.java
 * @author: zhangJ
 * @Date: 2020-01-14 15:44:19
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.mqtt.controller.WebController;


import com.github.pagehelper.Page;
import com.ztgm.base.aopAspect.FunLogType;
import com.ztgm.base.aopAspect.ManagerActionLog;
import com.ztgm.base.base.PageCondition;
import com.ztgm.base.util.Message;
import com.ztgm.base.util.PageUtil;
import com.ztgm.mqtt.dao.ProcompanyMapper;
import com.ztgm.mqtt.pojo.Procompany;
import com.ztgm.mqtt.service.ProcompanyService;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 公司表管理 ProcompanyController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2020-01-14 15:44:19
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/procompany")
public class ProcompanyController {
	@Autowired
	private ProcompanyService procompanyService;


	@RequestMapping("/manager")
	public String manager(Model model) {

		return "/backend/page/procompany/index.ftl";
	}

	@RequestMapping("/procompanyList")
	@ManagerActionLog(operateDescribe="查询公司表",operateFuncType=FunLogType.Query,operateModelClassName=ProcompanyMapper.class)
	@ResponseBody
	public String procompanyList( Procompany item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<Procompany> procompanys = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		procompanys = procompanyService.selectProcompanyList(item);

		try {
			rst = PageUtil.packageTableData(page, procompanys);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	@ManagerActionLog(operateDescribe="删除公司表",operateFuncType=FunLogType.Del,operateModelClassName=ProcompanyMapper.class)
	@ResponseBody
	public Message delete( Procompany item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = procompanyService.deleteProcompany(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		Procompany procompanys = procompanyService.selectProcompanyById(id);
		return JSONObject.fromObject(procompanys).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param procompany
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ManagerActionLog(operateDescribe="保存修改公司表",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=ProcompanyMapper.class)
	@ResponseBody
	public String saveOrUpdate(Procompany procompany) {

		JSONObject jsonObject = null;
		try {
			if (null == procompany.getId() || "".equals(procompany.getId())) {
				
				jsonObject = procompanyService.saveProcompany(procompany);

			} else {
				jsonObject = procompanyService.updateProcompany(procompany);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectprocompany")
    @ResponseBody
    public List<Procompany> selectprocompany( Procompany item) {
        return procompanyService.selectProcompanyList(item);
    }


}