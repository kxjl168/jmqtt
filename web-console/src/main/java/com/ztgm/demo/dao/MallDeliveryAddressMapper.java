package com.ztgm.demo.dao;
import java.util.List;

import com.ztgm.demo.pojo.MallDeliveryAddress;

public interface MallDeliveryAddressMapper {
    int deleteByPrimaryKey(String id);

    int insert(MallDeliveryAddress record);

    int insertSelective(MallDeliveryAddress record);

    MallDeliveryAddress selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MallDeliveryAddress record);

    int updateByPrimaryKey(MallDeliveryAddress record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<MallDeliveryAddress> selectList(MallDeliveryAddress item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-01-21 14:21:59
     */
    int delete(MallDeliveryAddress item);
}