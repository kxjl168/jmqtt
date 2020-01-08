package com.ztgm.base.dao;

import java.util.List;

import com.ztgm.base.pojo.SysOperLog;



public interface SysOperLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysOperLog record);

    int insertSelective(SysOperLog record);

    SysOperLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysOperLog record);

    int updateByPrimaryKey(SysOperLog record);
    
    
    
    //
	List<SysOperLog> selectList(SysOperLog item);

	int delete(SysOperLog record);

}