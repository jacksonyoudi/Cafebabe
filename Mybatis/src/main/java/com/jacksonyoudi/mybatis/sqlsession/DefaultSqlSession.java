package com.jacksonyoudi.mybatis.sqlsession;

import com.jacksonyoudi.mybatis.pojo.Configuration;
import com.jacksonyoudi.mybatis.pojo.MapperStatement;

import java.lang.reflect.*;
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
        if (list.size() == 1) {
            return (T) list.get(0);
        } else {
            throw new RuntimeException("result is not one");
        }
    }


    @Override
    public <T> T getMapper(Class<?> mapperClass) {

        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();

                String sid = className + "." + methodName;


                // 获取返回值类型
                Type returnType = method.getGenericReturnType();

                // 判断是否进行了 泛型类型参数化
                if (returnType instanceof ParameterizedType) {
                    return selectList(sid, args);
                } else {
                    return selectOne(sid, args);
                }
            }
        });


        return (T) proxyInstance;
    }
}
