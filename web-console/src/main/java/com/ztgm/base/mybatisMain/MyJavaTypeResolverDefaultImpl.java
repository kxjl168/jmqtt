package com.ztgm.base.mybatisMain;

import java.sql.Types;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

public class MyJavaTypeResolverDefaultImpl extends JavaTypeResolverDefaultImpl {

    public MyJavaTypeResolverDefaultImpl() {
        super();
        //把数据库的 timestamp 映射成 String
        super.typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("VARCHAR", new FullyQualifiedJavaType(String.class.getName())));
    }
}