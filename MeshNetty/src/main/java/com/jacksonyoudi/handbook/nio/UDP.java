package com.jacksonyoudi.handbook.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2022/01/15
 **/
public class UDP {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.socket().connect(new InetSocketAddress("127.0.0.1", 1899));

        channel.socket().bind(new InetSocketAddress("127.0.0.1", 1899));

    }
}
