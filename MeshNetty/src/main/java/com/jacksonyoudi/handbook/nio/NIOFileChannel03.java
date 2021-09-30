package com.jacksonyoudi.handbook.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/09/29
 **/
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {
        // 读
        File file = new File("1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel readChannel = fileInputStream.getChannel();
        ByteBuffer readBuffer = ByteBuffer.allocate((int) file.length());
        readChannel.read(readBuffer);

        readBuffer.flip();

        FileOutputStream outputStream = new FileOutputStream("2.txt");
        FileChannel writeChannel = outputStream.getChannel();
        writeChannel.write(readBuffer);
        fileInputStream.close();
        outputStream.close();
    }
}
