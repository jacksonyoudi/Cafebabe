package com.jacksonyoudi.handbook.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2022/01/15
 **/
public class S {
    public static void main(String[] args) throws IOException {
        // 获得一个套接字传输通道
        SocketChannel channel = SocketChannel.open();

        // 设置为非阻塞模式
        channel.configureBlocking(false);


//        在非阻塞情况下，与服务器的连接可能还没有真正建立，socketChannel.connect()方法就返回了，因此需要不断地自旋，检查当前是否连接到了主机：
        channel.connect(new InetSocketAddress("127.0.0.1", 8));

        while(!channel.finishConnect()) {
            // 不断地自旋，等待，或者做一些其他的事情
        }

    }
}
