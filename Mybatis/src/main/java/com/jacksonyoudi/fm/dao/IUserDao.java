package com.jacksonyoudi.fm.dao;

import com.jacksonyoudi.mybatis.pojo.User;

import java.io.IOException;
import java.util.List;

public interface IUserDao {
    public List<User> findAll() throws IOException;

    // 多条件组合查询
    public List<User> findByCondition(User user);

    // 多值查询
    public List<User> findByIds(int[] ids);
}
