package com.jacksonyoudi.mybatis.io;


import com.jacksonyoudi.mybatis.dao.IUserDao;
import com.jacksonyoudi.mybatis.pojo.User;
import com.jacksonyoudi.mybatis.sqlsession.SqlSesion;
import com.jacksonyoudi.mybatis.sqlsession.SqlSessionFactory;
import com.jacksonyoudi.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

class ResourcesTest {
    public static void main(String[] args) throws PropertyVetoException, DocumentException {
        try {
            InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            System.out.println(inputStream.toString());
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSesion sqlSesion = sessionFactory.openSqlSession();

            User user = new User();
            user.setId(1);
            user.setUsername("one");
            Object o = sqlSesion.selectOne("user.selectOne", user);
            System.out.println(o);


            IUserDao userDao = sqlSesion.getMapper(IUserDao.class);
            userDao.findAll();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}