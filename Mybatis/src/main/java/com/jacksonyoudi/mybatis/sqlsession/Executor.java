package com.jacksonyoudi.mybatis.sqlsession;

import com.jacksonyoudi.mybatis.pojo.Configuration;
import com.jacksonyoudi.mybatis.pojo.MapperStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {
    public <E> List<E> query(Configuration config, MapperStatement mapperStatement, Object... parameters) throws SQLException, Exception;
}
