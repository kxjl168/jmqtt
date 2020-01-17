package com.ztgm.openplat.dao;
import java.util.List;

import com.ztgm.openplat.pojo.RuleAction;

public interface RuleActionMapper {
    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:30
     */
    int deleteByPrimaryKey(Integer id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:30
     */
    int insert(RuleAction record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:30
     */
    int insertSelective(RuleAction record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:30
     */
    RuleAction selectByPrimaryKey(Integer id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:30
     */
    int updateByPrimaryKeySelective(RuleAction record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:30
     */
    int updateByPrimaryKey(RuleAction record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<RuleAction> selectList(RuleAction item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2020-01-10 10:29:30
     */
    int delete(RuleAction item);
}