package com.ztgm.base.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Component;

import com.ztgm.base.aopAspect.Authorization;
import com.ztgm.base.util.DateUtil;
import com.ztgm.base.util.RedisUtil;
import com.ztgm.openplat.util.UUIDUtil;

@Component
public class TokeUtils {

	@Autowired
	RedisUtil redisUtil;

	public final static String uidRdisKey = "uid";
	public final static String expireRdisKey = "expireTime";
	public final static Integer expireMin = 30;

	public boolean generateNewToken(String uid, String token) {

		String ntoken = UUIDUtil.getUUID();

		Map<String, String> datas = new HashMap<String, String>();
		datas.put(uidRdisKey, uid);
		datas.put(expireRdisKey, DateUtil.getDateStr(DateUtil.addByNum(new Date(), Calendar.MINUTE, expireMin), ""));

		redisUtil.setMap(ntoken, datas);

		return false;
	}

	/**
	 * 检查token
	 * 
	 * @param token
	 * @return
	 * @author zj
	 * @date 2020年1月14日
	 */
	public boolean checkToken(String token) {

		Map<String, String> datas = redisUtil.getMap(token);// , datas);
		if (datas == null)
			return false;
		else {
			Date expireDate = DateUtil.getDate(datas.get(expireRdisKey), "");
			if (!expireDate.after(new Date())) {
				
				redisUtil.remove(token);
				return false;
			}

			// 更新时间
			freshToken(token);
			return true;
		}
	}

	/**
	 * 刷新token过期时间
	 * 
	 * @param token
	 * @return
	 * @author zj
	 * @date 2020年1月14日
	 */
	public boolean freshToken(String token) {
		Map<String, String> datas = redisUtil.getMap(token);

		datas.put(expireRdisKey, DateUtil.getDateStr(DateUtil.addByNum(new Date(), Calendar.MINUTE, expireMin), ""));

		redisUtil.setMap(token, datas);
		return false;
	}

}
