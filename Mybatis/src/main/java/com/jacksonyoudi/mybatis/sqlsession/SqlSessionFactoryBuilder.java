package com.jacksonyoudi.mybatis.sqlsession;

import com.jacksonyoudi.mybatis.config.XMLConfigBuilder;
import com.jacksonyoudi.mybatis.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException, IOException {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration config = xmlConfigBuilder.parseConfig(in);
        return new DefaultSqlSessionFactory(config);
    }
}