package com.ztgm.mqtt.dao;
import java.util.List;

import com.ztgm.mqtt.pojo.Procompany;

public interface ProcompanyMapper {
    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-14 15:44:20
     */
    int deleteByPrimaryKey(String id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-14 15:44:20
     */
    int insert(Procompany record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-14 15:44:20
     */
    int insertSelective(Procompany record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-14 15:44:20
     */
    Procompany selectByPrimaryKey(String id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-14 15:44:20
     */
    int updateByPrimaryKeySelective(Procompany record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-14 15:44:20
     */
    int updateByPrimaryKey(Procompany record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<Procompany> selectList(Procompany item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2020-01-14 15:44:19
     */
    int delete(Procompany item);
}