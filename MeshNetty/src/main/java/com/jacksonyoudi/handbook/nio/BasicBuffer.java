package com.jacksonyoudi.handbook.nio;

import java.nio.IntBuffer;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/09/29
 **/
public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(5);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i * 2);
        }
        // 读写转换
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }


    }
}
