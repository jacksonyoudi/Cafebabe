package com.jacksonyoudi.mybatis.sqlsession;

import com.jacksonyoudi.mybatis.config.BoundSql;
import com.jacksonyoudi.mybatis.pojo.Configuration;
import com.jacksonyoudi.mybatis.pojo.MapperStatement;
import com.jacksonyoudi.mybatis.utils.GenericTokenParser;
import com.jacksonyoudi.mybatis.utils.ParameterMapping;
import com.jacksonyoudi.mybatis.utils.ParameterMappingTokenHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SimpleExcutor implements Executor {
    @Override
    public <E> List<E> query(Configuration config, MapperStatement mapperStatement, Object... parameters) throws Exception {
        Connection connection = config.getDataSource().getConnection();
        String sql = mapperStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        PreparedStatement prepareStatement = connection.prepareStatement(boundSql.getSqlText());

        String parameterType = mapperStatement.getParameterType();

        Class<?> parameterTypeClass =  getClassType(parameterType);

        List<ParameterMapping> mappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < mappingList.size(); i++) {
            ParameterMapping mapping = mappingList.get(0);
            String content = mapping.getContent();

            // reflect



        }


        return null;
    }


    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType == null) {
            return null;
        }

        Class<?> aClass = Class.forName(parameterType);
        return aClass;

    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", tokenHandler);

        String parseSql = parser.parse(sql);
        List<ParameterMapping> mappingList = tokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parseSql, mappingList);
        return boundSql;
    }
}
