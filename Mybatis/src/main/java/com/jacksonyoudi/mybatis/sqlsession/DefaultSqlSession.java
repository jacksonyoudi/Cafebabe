package com.jacksonyoudi.mybatis.sqlsession;

import com.jacksonyoudi.mybatis.pojo.Configuration;
import com.jacksonyoudi.mybatis.pojo.MapperStatement;

import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSesion {
    private Configuration config;

    public DefaultSqlSession(Configuration config) {
        this.config = config;
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... parameters) throws Exception {
        SimpleExcutor excutor = new SimpleExcutor();
        MapperStatement statement = config.getMapperStatement().get(statementid);

        List<Object> list = excutor.query(config, statement, parameters);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementid, Object... parameters) throws Exception {
        List<Object> list = selectList(statementid, parameters);
        if (list.size() == 0) {
            return (T) list.get(0);
        } else {
            throw new RuntimeException("result is not one");
        }
    }
}
