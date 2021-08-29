package com.jacksonyoudi.fm;

import com.jacksonyoudi.fm.dao.IUserDao;
import com.jacksonyoudi.mybatis.pojo.User;
import jdk.nashorn.internal.ir.CallNode;
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


    @Test
    public void test3() throws IOException {
        // Resources工具类，配置文件加载
        InputStream resourceAsStream = Resources.getResourceAsStream("sqluser_proxy.xml");
        // 2. 解析配置文件，创建fact
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3. 生产opensession openSession(true) 自动提交
        SqlSession sqlSession = factory.openSession();

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        User user1 = new User();
        user1.setId(10);

        List<User> all = mapper.findByCondition(user1);
        for (User user : all) {
            System.out.println(user);
        }

        sqlSession.close();

    }


    @Test
    public void test4() throws IOException {
        // Resources工具类，配置文件加载
        InputStream resourceAsStream = Resources.getResourceAsStream("sqluser_proxy.xml");
        // 2. 解析配置文件，创建fact
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3. 生产opensession openSession(true) 自动提交
        SqlSession sqlSession = factory.openSession();

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        int[] arr = {1, 2};

        List<User> all = mapper.findByIds(arr);
        for (User user : all) {
            System.out.println(user);
        }

        sqlSession.close();

    }
}
