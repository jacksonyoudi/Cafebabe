package com.jacksonyoudi.mybatis.io;


import java.io.IOException;
import java.io.InputStream;

class ResourcesTest {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            System.out.println(inputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}