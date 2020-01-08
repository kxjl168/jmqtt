package com.ztgm.base.dao;
import java.util.List;

import com.ztgm.base.pojo.SysPlat;

public interface SysPlatMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPlat record);

    int insertSelective(SysPlat record);

    SysPlat selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPlat record);

    int updateByPrimaryKey(SysPlat record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<SysPlat> selectList(SysPlat item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-01-11 15:20:49
     */
    int delete(SysPlat item);
}