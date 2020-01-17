package com.ztgm.openplat.dao;
import java.util.List;

import com.ztgm.openplat.pojo.Rule;

public interface RuleMapper {
    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:10
     */
    int deleteByPrimaryKey(Integer id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:10
     */
    int insert(Rule record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:10
     */
    int insertSelective(Rule record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:10
     */
    Rule selectByPrimaryKey(Integer id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:10
     */
    int updateByPrimaryKeySelective(Rule record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2020-01-10 10:29:10
     */
    int updateByPrimaryKey(Rule record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<Rule> selectList(Rule item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2020-01-10 10:29:09
     */
    int delete(Rule item);
}