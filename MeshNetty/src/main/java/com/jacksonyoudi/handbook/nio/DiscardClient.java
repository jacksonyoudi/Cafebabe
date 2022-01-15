package com.jacksonyoudi.handbook.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2022/01/15
 **/
public class DiscardClient {
    public static void startClient() throws IOException {
        InetSocketAddress address = new InetSocketAddress(18999);
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(address);

        // 自旋
        while (!channel.finishConnect()) {

        }
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello".getBytes());

        channel.write(buffer);
        channel.shutdownInput();
        channel.close();
    }


    public static void main(String[] args) throws IOException {
        startClient();
    }
}
