package com.ztgm.base.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.ztgm.base.base.PageCondition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author handsomeXiao
 * @date 2018/6/7 15:19
 */
public class PageUtil {

	public static final int DEFAULT_PAGE_NO = 1;

	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final int DEFAULT_OFFSET = 0;

	public static Page getPage(PageCondition pageCondition) {
		String offset = pageCondition.getOffset();
		String pageSize = pageCondition.getPageSize();
		String pagenum=pageCondition.getPagenum();
		int pageNo = DEFAULT_PAGE_NO;
		int pgSize;

		if(null==pageSize || "".equals(pageSize)){
			pgSize = DEFAULT_PAGE_SIZE;
		}else{
			try {
				pgSize = Integer.parseInt(pageSize);
			}catch (Exception e){
				pgSize = DEFAULT_PAGE_SIZE;
			}

		}

	
		
		if(pagenum!=null&&!pagenum.equals("")) {
			pageNo=Integer.parseInt(pagenum);
			return PageHelper.startPage(pageNo, pgSize);
		}
		else
			return getPage(offset, pageSize);
	}
	
/*	
	public static Page getPage(PageCondition pageCondition) {
		String offset = pageCondition.getOffset();
		String pageSize = pageCondition.getPageSize();
		return getPage(offset, pageSize);
	}
	
	*/

	public static Page getPage(String offset, String pageSize) {
		int pageNo = DEFAULT_PAGE_NO;
		int defaultPageSize = DEFAULT_PAGE_SIZE;
		int defaultOffset = DEFAULT_OFFSET;
		try {
			if (!isBlank(offset)) {
				defaultOffset = Integer.parseInt(offset);
			}
			if (!isBlank(pageSize)) {
				defaultPageSize = Integer.parseInt(pageSize);
			}
			pageNo = (int) Math.ceil(((double) (defaultOffset + 1)) / ((double) defaultPageSize));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return PageHelper.startPage(pageNo, defaultPageSize);
	}

	
	public static JSONObject packageJSONData(Page page, List dataList) throws JSONException {
		String resultString = "";
		Gson gs = new Gson();
		JSONObject data = new JSONObject();
		JSONArray rows = new JSONArray(gs.toJson(dataList));
		data.put("total", page.getTotal());
		data.put("rows", rows);
		
		return data;
	}
	
	public static <T> String packageTableData(Page page, List<T> dataList) throws JSONException {
		String resultString = "";
		Gson gs = new Gson();
		JSONObject data = new JSONObject();
		JSONArray rows = new JSONArray(gs.toJson(dataList));
		data.put("total", page.getTotal());
		data.put("rows", rows);
		resultString = data.toString();
		return resultString;
	}
}
