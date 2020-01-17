package com.ztgm.openplat.service.impl;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ztgm.openplat.util.ExceptionUntil;
import com.ztgm.openplat.util.UUIDUtil;
import com.ztgm.base.util.AppResult;
import com.ztgm.base.util.AppResultUtil;
import com.ztgm.openplat.dao.RuleMapper;
import com.ztgm.openplat.pojo.Rule;
import com.ztgm.openplat.service.RuleService;

import java.util.*;

@Service
public class RuleServiceImpl implements RuleService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RuleMapper itemMapper;

	/**
	 * @param item
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject saveRule(Rule item) {
		JSONObject rtn = new JSONObject();

		/*
		 * if (null == item || null == item.getPassword() || null ==
		 * item.getTelephone()) { rtn.put("bol", false); rtn.put("message",
		 * "手机号码或者密码为空"); return rtn; }
		 */
		try {

			item.setId(UUIDUtil.getLongUUID());

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
	public JSONObject updateRule(Rule item) {
		JSONObject rtn = new JSONObject();

		if (null == item || null == item.getId()) {
			rtn.put("bol", false);
			rtn.put("message", "id为空");
			return rtn;
		}

		try {
			itemMapper.updateByPrimaryKeySelective(item);

			//TODO
			//notifyMqttRuleChanged()
			
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
	public List<Rule> selectRuleList(Rule item) {
		List<Rule> itemList = new ArrayList<>();
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
	public int deleteRule(Rule item) {
		int result = 0;
		try {

			result = itemMapper.delete(item);
			
			
			//TODO
			//notifyMqttRuleChanged()
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除出错");
			log.error(ExceptionUntil.getMessage(e));
		}
		return result;
	}

	@Override
	public Rule selectRuleById(String id) {
		Rule data = null;

		Rule query = new Rule();

		Long lid = Long.parseLong(id);
		query.setId(lid);

		List<Rule> datas = selectRuleList(query);
		if (datas != null && datas.size() > 0) {
			data = datas.get(0);

		}
		return data;

	}

	/**
	 * 启动规则，通知jmqtt，然后更新本地状态
	 * 
	 * @param rule
	 * @return
	 * @author zj
	 * @date 2020年1月10日
	 */
	public AppResult startRule(Rule rule) {
		AppResult rst = AppResultUtil.fail();

		// TODO

		return rst;
	}

	/**
	 * 停止规则，通知jmqtt，然后跟新本地状态
	 * 
	 * @param rule
	 * @return
	 * @author zj
	 * @date 2020年1月10日
	 */
	public AppResult stopRule(Rule rule) {
		AppResult rst = AppResultUtil.fail();

		// TODO

		return rst;
	}

	/**
	 * 根据productKey通知jmqtt规则变化
	 * 
	 * @param productKey
	 * @return
	 * @author zj
	 * @date 2020年1月10日
	 */
	public AppResult notifyMqttRuleChanged(String productKey) {
		AppResult rst = AppResultUtil.fail();

		// TODO

		return rst;
	}

}
