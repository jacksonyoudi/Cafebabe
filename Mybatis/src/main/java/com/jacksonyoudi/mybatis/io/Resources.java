package com.jacksonyoudi.mybatis.io;

import java.io.IOException;
import java.io.InputStream;

public class Resources {
    public static InputStream getResourceAsStream(String path) throws IOException {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
