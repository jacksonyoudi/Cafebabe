package com.jacksonyoudi.mybatis.dao;

import com.jacksonyoudi.mybatis.io.Resources;
import com.jacksonyoudi.mybatis.jdbc.User;
import com.jacksonyoudi.mybatis.sqlsession.SqlSesion;
import com.jacksonyoudi.mybatis.sqlsession.SqlSessionFactory;
import com.jacksonyoudi.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class UserDao implements IUserDao {
    @Override
    public List<User> findAll() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSesion sqlSesion = sessionFactory.openSqlSession();

        List<User> list = sqlSesion.selectList("user.selectList");

        return list;
    }

    @Override
    public User findByCondition(User user) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSesion sqlSesion = sessionFactory.openSqlSession();

        User one = sqlSesion.selectOne("user.selectOne", user);

        return one;
    }
}
