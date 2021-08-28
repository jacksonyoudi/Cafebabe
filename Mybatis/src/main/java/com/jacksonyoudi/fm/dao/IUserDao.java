package com.jacksonyoudi.fm.dao;

import com.jacksonyoudi.mybatis.pojo.User;

import java.io.IOException;
import java.util.List;

public interface IUserDao {
    public List<User> findAll() throws IOException;
}
