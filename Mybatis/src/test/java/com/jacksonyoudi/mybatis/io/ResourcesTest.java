package com.jacksonyoudi.mybatis.io;

import com.jacksonyoudi.mybatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;

class ResourcesTest {
    public  void  test() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}