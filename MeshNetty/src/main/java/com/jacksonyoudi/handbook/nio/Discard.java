package com.jacksonyoudi.handbook.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2022/01/15
 **/
public class Discard {
    public static void startServer() throws IOException {
        // 创建选择器
        Selector selector = Selector.open();

        // 获取通道
        ServerSocketChannel channel = ServerSocketChannel.open();

        // 设置非阻塞
        channel.configureBlocking(false);

        channel.bind(new InetSocketAddress(18999));

        // 通道注册到selector上

        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // 如果是 连接就绪，就获取客户端连接
                    SocketChannel client = channel.accept();
                    client.configureBlocking(false);

                    // 将新的客户端的通道注册到 选择器上
                    client.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 可读状态
                    SocketChannel readChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int length = 0;

                    while ((length = readChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, length));
                        buffer.clear();
                    }
                    readChannel.close();
                }
                iterator.remove();
            }
        }
        channel.close();
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}