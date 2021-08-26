package com.jacksonyoudi.mybatis.sqlsession;

import com.jacksonyoudi.mybatis.pojo.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration config;

    @Override
    public SqlSesion openSqlSession() {
        return new DefaultSqlSession();
    }
}
