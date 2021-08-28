package com.jacksonyoudi.mybatis.dao;

import com.jacksonyoudi.mybatis.jdbc.User;

import java.util.List;

public interface IUserDao {
    public List<User> findAll() throws Exception;

    public User findByCondition(User user) throws Exception;

}
