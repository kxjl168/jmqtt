/*
 * @(#)SysOperLogController.java
 * @author: zhangJ
 * @Date: 2018/12/11 09:02
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.controller.WebController;


import com.github.pagehelper.Page;
import com.ztgm.base.aopAspect.FunLogType;
import com.ztgm.base.aopAspect.ManagerActionLog;
import com.ztgm.base.base.PageCondition;
import com.ztgm.base.pojo.SysOperLog;
import com.ztgm.base.service.SysLogService;
import com.ztgm.base.util.Message;
import com.ztgm.base.util.PageUtil;

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
 * 操作日志管理 SysOperLogController.java.
 * 
 * @author zj
 * @version 1.0.1 2018年12月11日
 * @revision zj 2018年12月11日
 * @since 1.0.1
 */
@Controller
@RequestMapping("/manager/sysOperLog")
public class SysOperLogController {
	@Autowired
	private SysLogService lockSysOperLogService;


	
	@RequestMapping("/manager")
	public String manager(Model model) {

		
		
		return "/backend/sysOperLog/index.ftl";
	}

	@RequestMapping("/lockSysOperLogList")
	//@ManagerActionLog(operateDescribe="查询系统日志",operateFuncType=FunLogType.Query,operateModelClassName=SysOperLogMapper.class)
	@ResponseBody
	public String lockSysOperLogList( SysOperLog item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<SysOperLog> lockSysOperLogs = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		lockSysOperLogs = lockSysOperLogService.selectSysOperLogList(item);

		try {
			rst = PageUtil.packageTableData(page, lockSysOperLogs);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除系统日志",operateFuncType=FunLogType.Del,operateModelClassName=SysOperLogMapper.class)
	@ResponseBody
	public Message delete( SysOperLog item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = lockSysOperLogService.deleteSysOperLog(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		SysOperLog lockSysOperLogs = lockSysOperLogService.selectSysOperLogById(id);
		return JSONObject.fromObject(lockSysOperLogs).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param lockSysOperLog
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改系统日志",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=SysOperLogMapper.class)
	@ResponseBody
	public String saveOrUpdate(SysOperLog lockSysOperLog) {

		JSONObject jsonObject = null;
		try {
			if (null == lockSysOperLog.getId() || "".equals(lockSysOperLog.getId())) {
				
				jsonObject = lockSysOperLogService.saveSysOperLog(lockSysOperLog);

			} else {
				jsonObject = lockSysOperLogService.updateSysOperLog(lockSysOperLog);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectlockSysOperLog")
    @ResponseBody
    public List<SysOperLog> selectlockSysOperLog( SysOperLog item) {
        return lockSysOperLogService.selectSysOperLogList(item);
    }


}