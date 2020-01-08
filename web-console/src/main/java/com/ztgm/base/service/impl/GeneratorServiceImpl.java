package com.ztgm.base.service.impl;

import com.ztgm.base.service.GeneratorService;
import com.ztgm.base.pojo.AField;
import com.ztgm.base.pojo.Generator;
import com.ztgm.base.dao.GeneratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneratorServiceImpl implements GeneratorService{

    @Autowired
    private GeneratorMapper generatorMapper;

    @Override
    public List<Generator> selectGeneratorList(Generator generator) {
        return generatorMapper.selectGeneratorList(generator);
    }
    
    
    /**
     * 查询表字段
     * @param generator
     * @return
     * @author zj
     * @date 2019年1月8日
     */
    public List<AField> selectTableColList(Generator generator){
    	  return generatorMapper.selectTableColList(generator);
    }
}
