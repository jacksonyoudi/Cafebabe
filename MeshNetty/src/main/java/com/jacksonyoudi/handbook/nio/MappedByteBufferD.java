package com.jacksonyoudi.handbook.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/09/29
 **/
public class MappedByteBufferD {
    public static void main(String[] args) throws Exception {
        RandomAccessFile accessFile = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = accessFile.getChannel();

        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        buffer.put(0, (byte) 'H');
        buffer.put(3, (byte) '9');

        accessFile.close();
        System.out.println("modify success");
    }
}
