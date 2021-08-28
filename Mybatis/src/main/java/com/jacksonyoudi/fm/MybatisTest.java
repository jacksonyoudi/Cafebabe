package com.jacksonyoudi.fm;

import com.jacksonyoudi.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqluser.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = factory.openSession();
//        List<User> users = sqlSession.selectList("user.findAll");
//
//        for (User user : users) {
//            System.out.println(user);
//        }

        int insert = sqlSession.insert("user.deleteUser", 1);
        System.out.println(insert);

        sqlSession.commit();
        sqlSession.close();

    }
}
