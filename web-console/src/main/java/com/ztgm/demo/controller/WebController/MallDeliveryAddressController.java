/*
 * @(#)MallDeliveryAddressController.java
 * @author: zhangJ
 * @Date: 2019-01-21 14:21:59
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
import com.ztgm.demo.dao.MallDeliveryAddressMapper;
import com.ztgm.demo.pojo.MallDeliveryAddress;
import com.ztgm.demo.service.MallDeliveryAddressService;

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
 * 收货地址管理 MallDeliveryAddressController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-01-21 14:21:59
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/malldeliveryaddress")
public class MallDeliveryAddressController {
	@Autowired
	private MallDeliveryAddressService malldeliveryaddressService;


	@RequestMapping("/manager")
	public String manager(Model model) {

		return "/backend/page/malldeliveryaddress/index.ftl";
	}

	@RequestMapping("/malldeliveryaddressList")
	@ManagerActionLog(operateDescribe="查询收货地址",operateFuncType=FunLogType.Query,operateModelClassName=MallDeliveryAddressMapper.class)
	@ResponseBody
	public String malldeliveryaddressList( MallDeliveryAddress item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<MallDeliveryAddress> malldeliveryaddresss = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		malldeliveryaddresss = malldeliveryaddressService.selectMallDeliveryAddressList(item);

		try {
			rst = PageUtil.packageTableData(page, malldeliveryaddresss);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	@ManagerActionLog(operateDescribe="删除收货地址",operateFuncType=FunLogType.Del,operateModelClassName=MallDeliveryAddressMapper.class)
	@ResponseBody
	public Message delete( MallDeliveryAddress item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = malldeliveryaddressService.deleteMallDeliveryAddress(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		MallDeliveryAddress malldeliveryaddresss = malldeliveryaddressService.selectMallDeliveryAddressById(id);
		return JSONObject.fromObject(malldeliveryaddresss).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param malldeliveryaddress
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ManagerActionLog(operateDescribe="保存修改收货地址",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=MallDeliveryAddressMapper.class)
	@ResponseBody
	public String saveOrUpdate(MallDeliveryAddress malldeliveryaddress) {

		JSONObject jsonObject = null;
		try {
			if (null == malldeliveryaddress.getId() || "".equals(malldeliveryaddress.getId())) {
				
				jsonObject = malldeliveryaddressService.saveMallDeliveryAddress(malldeliveryaddress);

			} else {
				jsonObject = malldeliveryaddressService.updateMallDeliveryAddress(malldeliveryaddress);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectmalldeliveryaddress")
    @ResponseBody
    public List<MallDeliveryAddress> selectmalldeliveryaddress( MallDeliveryAddress item) {
        return malldeliveryaddressService.selectMallDeliveryAddressList(item);
    }


}