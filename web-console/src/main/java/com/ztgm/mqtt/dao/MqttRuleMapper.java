package com.ztgm.mqtt.dao;
import java.util.List;

import com.ztgm.mqtt.pojo.MqttRule;

public interface MqttRuleMapper {
    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-08 16:29:51
     */
    int deleteByPrimaryKey(String id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-08 16:29:51
     */
    int insert(MqttRule record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-08 16:29:51
     */
    int insertSelective(MqttRule record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-08 16:29:51
     */
    MqttRule selectByPrimaryKey(String id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-08 16:29:51
     */
    int updateByPrimaryKeySelective(MqttRule record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-08 16:29:51
     */
    int updateByPrimaryKey(MqttRule record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<MqttRule> selectList(MqttRule item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2020-01-08 16:29:51
     */
    int delete(MqttRule item);
}