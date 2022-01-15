package com.jacksonyoudi.handbook.nio.reactor;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2022/01/15
 **/
public class EchoServerReactor implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public EchoServerReactor() throws ClosedChannelException {

        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptorHandler());

    }

    @Override
    public void run() {

    }
}
