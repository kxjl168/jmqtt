package com.ztgm.demo.controller.AppController;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.ztgm.base.pojo.SysPlat;
import com.ztgm.base.util.AppResult;
import com.ztgm.base.util.AppResultUtil;
import com.ztgm.base.service.SysPlatService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 首页接口-商品楼层-楼层详情等
 * 
 * @author zj
 * @date 2018年6月25日
 *
 */
@Controller
@RequestMapping("/interface/index")
@PropertySource("classpath:project.properties")
public class MobileIndexController {

	@Value("${HTTP_PATH}")
	private String FILE_SVR_HTTP_OUTER_PATH;

	@Value("${FILE_SVR_PATH}")
	private String FILE_SVR_PATH;

	private Logger logger = Logger.getLogger(MobileIndexController.class);

	@Autowired
	private SysPlatService sysPlatService;

	@ResponseBody
	@RequestMapping(value = "/test")
	public AppResult test(HttpServletRequest request, HttpServletResponse response) {
		try {

			logger.info("/***********test visit************");

			Enumeration<String> headers = request.getHeaderNames();
			while (headers.hasMoreElements()) {
				String key = headers.nextElement();
				logger.info("*" + key + ":" + request.getHeader(key));
			}
			
			
			logger.info("/***********test visit data************");
			Enumeration paras = request.getParameterNames();
			while (paras.hasMoreElements()) {

				String v = (String) paras.nextElement();

				
				logger.info("*" + v + ":" +  request.getParameter(v));
			}


			logger.info("*************test visit end*******/");
			
			
		

			try {
				InputStream instream = request.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "utf-8"));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				reader.close();

				instream.close();
				String data = sb.toString();
				logger.info("body:" + data);

			} catch (Exception e) {
				logger.error("body:error" +e.getMessage());
			}

			return AppResultUtil.success();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AppResultUtil.fail();
		}
	}

	/**
	 * 移动端-首页热门商品<br>
	 * {@link com.ztgm.mall.test#testHotgoods()}
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author zj
	 * @date 2018年6月25日
	 */
	@ResponseBody
	@RequestMapping(value = "/hotgoods")
	public AppResult hotgoods(HttpServletRequest request, HttpServletResponse response) {
		try {

			List<SysPlat> hotgoods = new ArrayList();

			hotgoods = sysPlatService.selectSysPlatList(null);

			return AppResultUtil.success(hotgoods);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AppResultUtil.fail();
		}
	}

}
