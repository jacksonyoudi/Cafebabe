package com.jacksonyoudi.mybatis.sqlsession;

import com.jacksonyoudi.mybatis.config.BoundSql;
import com.jacksonyoudi.mybatis.pojo.Configuration;
import com.jacksonyoudi.mybatis.pojo.MapperStatement;
import com.jacksonyoudi.mybatis.utils.GenericTokenParser;
import com.jacksonyoudi.mybatis.utils.ParameterMapping;
import com.jacksonyoudi.mybatis.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SimpleExcutor implements Executor {
    @Override
    public <E> List<E> query(Configuration config, MapperStatement mapperStatement, Object... parameters) throws Exception {
        Connection connection = config.getDataSource().getConnection();
        String sql = mapperStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        PreparedStatement prepareStatement = connection.prepareStatement(boundSql.getSqlText());

        String parameterType = mapperStatement.getParameterType();

        Class<?> parameterTypeClass = getClassType(parameterType);

        List<ParameterMapping> mappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < mappingList.size(); i++) {
            ParameterMapping mapping = mappingList.get(i);
            String content = mapping.getContent();

            // reflect
            Field field = parameterTypeClass.getDeclaredField(content);
            field.setAccessible(true);
            Object o = field.get(parameters[0]);
            prepareStatement.setObject(i + 1, o);
        }

        // excutor sql

        ResultSet resultSet = prepareStatement.executeQuery();


        String resultType = mapperStatement.getResultType();
        Class<?> resultClass = getClassType(resultType);

        ArrayList<Object> objects = new ArrayList<>();

        while (resultSet.next()) {
            // 元数据
            ResultSetMetaData metaData = resultSet.getMetaData();

            Object o1 = resultClass.newInstance();
            for (int j = 1; j <= metaData.getColumnCount(); j++) {
                String columnName = metaData.getColumnName(j);

                Object value = resultSet.getObject(columnName);

                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o1, value);
            }

            objects.add(o1);

        }

        return (List<E>) objects;
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
