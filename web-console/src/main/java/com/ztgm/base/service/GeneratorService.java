package com.ztgm.base.service;

import com.ztgm.base.pojo.AField;
import com.ztgm.base.pojo.Generator;

import java.util.List;

public interface GeneratorService {

    List<Generator> selectGeneratorList(Generator generator);
    
    /**
     * 查询表字段
     * @param generator
     * @return
     * @author zj
     * @date 2019年1月8日
     */
    List<AField> selectTableColList(Generator generator);
}
