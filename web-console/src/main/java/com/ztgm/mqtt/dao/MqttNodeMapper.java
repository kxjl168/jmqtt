package com.ztgm.mqtt.dao;
import java.util.List;

import com.ztgm.mqtt.pojo.MqttNode;

public interface MqttNodeMapper {
    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-06 13:51:43
     */
    int deleteByPrimaryKey(String id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-06 13:51:43
     */
    int insert(MqttNode record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-06 13:51:43
     */
    int insertSelective(MqttNode record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-06 13:51:43
     */
    MqttNode selectByPrimaryKey(String id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-06 13:51:43
     */
    int updateByPrimaryKeySelective(MqttNode record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-06 13:51:43
     */
    int updateByPrimaryKey(MqttNode record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<MqttNode> selectList(MqttNode item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2020-01-06 13:51:43
     */
    int delete(MqttNode item);
}