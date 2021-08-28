package com.jacksonyoudi.mybatis.sqlsession;

import java.sql.SQLException;
import java.util.List;

public interface SqlSesion {
    // 可变参数
    public <E> List<E> selectList(String statementid, Object... parameters) throws Exception;

    public <T> T selectOne(String statementid, Object... parameters) throws Exception;

    public  <T> T getMapper(Class<?> mapper);

}
