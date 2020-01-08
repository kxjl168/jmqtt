/*
 * @(#)TestController.java
 * @author: zhangJ
 * @Date: 2019-02-27 11:59:56
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.demo.controller.WebController;


import com.github.pagehelper.Page;
import com.ztgm.base.aopAspect.FunLogType;
import com.ztgm.base.aopAspect.ManagerActionLog;
import com.ztgm.base.base.PageCondition;
import com.ztgm.base.util.Message;
import com.ztgm.base.util.PageUtil;
import com.ztgm.demo.dao.TestMapper;
import com.ztgm.demo.pojo.Test;
import com.ztgm.demo.service.TestService;

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
 * 测试表管理 TestController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-02-27 11:59:56
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/test")
public class TestController {
	@Autowired
	private TestService testService;


	@RequestMapping("/manager")
	public String manager(Model model) {

		return "/backend/page/test/index.ftl";
	}

	@RequestMapping("/testList")
	@ManagerActionLog(operateDescribe="查询测试表",operateFuncType=FunLogType.Query,operateModelClassName=TestMapper.class)
	@ResponseBody
	public String testList( Test item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<Test> tests = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		tests = testService.selectTestList(item);

		try {
			rst = PageUtil.packageTableData(page, tests);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	@ManagerActionLog(operateDescribe="删除测试表",operateFuncType=FunLogType.Del,operateModelClassName=TestMapper.class)
	@ResponseBody
	public Message delete( Test item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = testService.deleteTest(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		Test tests = testService.selectTestById(id);
		return JSONObject.fromObject(tests).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param test
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ManagerActionLog(operateDescribe="保存修改测试表",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=TestMapper.class)
	@ResponseBody
	public String saveOrUpdate(Test test) {

		JSONObject jsonObject = null;
		try {
			if (null == test.getId() || "".equals(test.getId())) {
				
				jsonObject = testService.saveTest(test);

			} else {
				jsonObject = testService.updateTest(test);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selecttest")
    @ResponseBody
    public List<Test> selecttest( Test item) {
        return testService.selectTestList(item);
    }


}