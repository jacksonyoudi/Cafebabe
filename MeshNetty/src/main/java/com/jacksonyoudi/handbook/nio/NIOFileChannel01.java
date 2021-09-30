package com.jacksonyoudi.handbook.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/09/29
 **/
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        String s = "hello, jackson youdi";
        // 输出流
        FileOutputStream fileOutputStream = new FileOutputStream("1.txt");
        FileChannel channel = fileOutputStream.getChannel();

        // 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(s.getBytes());

        buffer.flip();

        channel.write(buffer);
//        fileOutputStream.flush();
        fileOutputStream.close();


    }
}
