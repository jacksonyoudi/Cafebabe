package com.jacksonyoudi.handbook.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/09/29
 **/
public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {
        File file = new File("1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());

        channel.read(buffer);

        System.out.println(new String(buffer.array()));

        fileInputStream.close();

    }
}
