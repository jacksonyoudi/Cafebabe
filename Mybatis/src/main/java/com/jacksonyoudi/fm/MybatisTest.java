package com.jacksonyoudi.fm;

import com.jacksonyoudi.fm.dao.IUserDao;
import com.jacksonyoudi.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void test1() throws IOException {
        // Resources工具类，配置文件加载
        InputStream resourceAsStream = Resources.getResourceAsStream("sqluser.xml");
        // 2. 解析配置文件，创建fact
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3. 生产opensession openSession(true) 自动提交
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


    @Test
    public void test2() throws IOException {
        // Resources工具类，配置文件加载
        InputStream resourceAsStream = Resources.getResourceAsStream("sqluser_proxy.xml");
        // 2. 解析配置文件，创建fact
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3. 生产opensession openSession(true) 自动提交
        SqlSession sqlSession = factory.openSession();

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }

        sqlSession.close();

    }


}
