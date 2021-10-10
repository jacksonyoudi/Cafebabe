package com.jacksonyoudi.handbook.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketchannel = SocketChannel.open();

        socketchannel.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);

        if (!socketchannel.connect(address)) {
            while( !socketchannel.finishConnect()) {
                System.out.println("没有连接，不阻塞,可以做其他工作");
            }
        }

        ByteBuffer byteBuffer = ByteBuffer.wrap("hello, jackson".getBytes());

        socketchannel.write(byteBuffer);


    }
}
