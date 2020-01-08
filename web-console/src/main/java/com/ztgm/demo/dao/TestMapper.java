package com.ztgm.demo.dao;
import java.util.List;

import com.ztgm.demo.pojo.Test;

public interface TestMapper {
    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2019-02-27 11:59:56
     */
    int deleteByPrimaryKey(String id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2019-02-27 11:59:56
     */
    int insert(Test record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2019-02-27 11:59:56
     */
    int insertSelective(Test record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2019-02-27 11:59:56
     */
    Test selectByPrimaryKey(String id);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2019-02-27 11:59:56
     */
    int updateByPrimaryKeySelective(Test record);

    /**
    * method with xml 
    * @param 
    * @return
    * @author KAutoGenerator
    * @date 2019-02-27 11:59:56
     */
    int updateByPrimaryKey(Test record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<Test> selectList(Test item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-02-27 11:59:56
     */
    int delete(Test item);
}