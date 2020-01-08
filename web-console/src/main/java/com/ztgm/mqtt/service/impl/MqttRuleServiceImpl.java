package com.ztgm.mqtt.service.impl;



import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ztgm.base.util.ExceptionUntil;
import com.ztgm.base.util.UUIDUtil;
import com.ztgm.mqtt.dao.MqttRuleMapper;
import com.ztgm.mqtt.pojo.MqttRule;
import com.ztgm.mqtt.service.MqttRuleService;

import java.util.*;

@Service
public class MqttRuleServiceImpl implements MqttRuleService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MqttRuleMapper itemMapper;

	/**
	 * @param item
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject saveMqttRule(MqttRule item) {
		JSONObject rtn = new JSONObject();

		/*
		 * if (null == item || null == item.getPassword() || null ==
		 * item.getTelephone()) { rtn.put("bol", false); rtn.put("message",
		 * "手机号码或者密码为空"); return rtn; }
		 */
		try {

				item.setId(UUIDUtil.getUUID());

			itemMapper.insertSelective(item);

			rtn.put("bol", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("新增失败", e);
			rtn.put("bol", false);
			rtn.put("message", "新增失败");
			log.error(ExceptionUntil.getMessage(e));
			return rtn;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateMqttRule(MqttRule item) {
		JSONObject rtn = new JSONObject();

		if (null == item || null == item.getId()) {
			rtn.put("bol", false);
			rtn.put("message", "id为空");
			return rtn;
		}

		try {
			itemMapper.updateByPrimaryKeySelective(item);

			rtn.put("bol", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("更新出错", e);
			rtn.put("bol", false);
			rtn.put("message", "更新出错");
			return rtn;
		}
	}

	@Override
	public List<MqttRule> selectMqttRuleList(MqttRule item) {
		List<MqttRule> itemList = new ArrayList<>();
		try {
			itemList = itemMapper.selectList(item);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询列表出错");
			log.error(ExceptionUntil.getMessage(e));
		}
		return itemList;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteMqttRule(MqttRule item) {
		int result = 0;
		try {

			result = itemMapper.delete(item);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除出错");
			log.error(ExceptionUntil.getMessage(e));
		}
		return result;
	}

	@Override
	public MqttRule selectMqttRuleById(String id) {
		MqttRule data = null;

		MqttRule query = new MqttRule();
	
			query.setId(id);
		
		

		List<MqttRule> datas = selectMqttRuleList(query);
		if (datas != null && datas.size() > 0) {
			data = datas.get(0);

		}
		return data;

	}

}
