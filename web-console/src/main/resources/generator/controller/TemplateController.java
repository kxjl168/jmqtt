/*
 * @(#)${modelClass}Controller.java
 * @author: zhangJ
 * @Date: ${generatedate}
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package ${basepackageName}.controller.WebController;


import com.github.pagehelper.Page;
import com.ztgm.base.aopAspect.FunLogType;
import com.ztgm.base.aopAspect.ManagerActionLog;
import com.ztgm.base.base.PageCondition;
import com.ztgm.base.util.Message;
import com.ztgm.base.util.PageUtil;
import ${basepackageName}.dao.${modelClass}Mapper;
import ${basepackageName}.pojo.${modelClass};
import ${basepackageName}.service.${modelClass}Service;

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
 * ${logName}管理 ${modelClass}Controller.java.
 * 
 * @author ${author}
 * @version 1.0.1 ${generatedate}
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/${ctrollerModelMapping}")
public class ${modelClass}Controller {
	@Autowired
	private ${modelClass}Service ${ctrollerModelMapping}Service;


	@RequestMapping("/manager")
	public String manager(Model model) {

		return "/backend/page/${ctrollerModelMapping}/index.ftl";
	}

	@RequestMapping("/${ctrollerModelMapping}List")
	@ManagerActionLog(operateDescribe="查询${logName}",operateFuncType=FunLogType.Query,operateModelClassName=${modelClass}Mapper.class)
	@ResponseBody
	public String ${ctrollerModelMapping}List( ${modelClass} item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<${modelClass}> ${ctrollerModelMapping}s = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		${ctrollerModelMapping}s = ${ctrollerModelMapping}Service.select${modelClass}List(item);

		try {
			rst = PageUtil.packageTableData(page, ${ctrollerModelMapping}s);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	@ManagerActionLog(operateDescribe="删除${logName}",operateFuncType=FunLogType.Del,operateModelClassName=${modelClass}Mapper.class)
	@ResponseBody
	public Message delete( ${modelClass} item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = ${ctrollerModelMapping}Service.delete${modelClass}(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		${modelClass} ${ctrollerModelMapping}s = ${ctrollerModelMapping}Service.select${modelClass}ById(id);
		return JSONObject.fromObject(${ctrollerModelMapping}s).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param ${ctrollerModelMapping}
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ManagerActionLog(operateDescribe="保存修改${logName}",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=${modelClass}Mapper.class)
	@ResponseBody
	public String saveOrUpdate(${modelClass} ${ctrollerModelMapping}) {

		JSONObject jsonObject = null;
		try {
			if (null == ${ctrollerModelMapping}.getId() || "".equals(${ctrollerModelMapping}.getId())) {
				
				jsonObject = ${ctrollerModelMapping}Service.save${modelClass}(${ctrollerModelMapping});

			} else {
				jsonObject = ${ctrollerModelMapping}Service.update${modelClass}(${ctrollerModelMapping});
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/select${ctrollerModelMapping}")
    @ResponseBody
    public List<${modelClass}> select${ctrollerModelMapping}( ${modelClass} item) {
        return ${ctrollerModelMapping}Service.select${modelClass}List(item);
    }


}