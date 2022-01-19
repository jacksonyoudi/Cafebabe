package com.crazymakercircle.ReactorModel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static java.nio.channels.Selector.*;

public class EchoServerReactor implements Runnable {
    Selector selector;

    ServerSocketChannel serverSocketChannel;

    public EchoServerReactor() throws IOException {
        // 创建 selector
        selector = open();

        serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(19988);

        serverSocketChannel.socket().bind(address);

        System.out.println("服务器已经开始监听");

        // 设置异步
        serverSocketChannel.configureBlocking(false);

        //  注册一个 accept事件，返回一个 sk
        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //  将 AcceptorHandler实例和 sk进行绑定
        sk.attach(new AcceptorHandler());
    }


    @Override
    public void run() {
        try {
            // 如果线程不终端
            while (!Thread.interrupted()) {
                // 阻塞， 有就绪才会
                selector.select();
                // IO事件的 key
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                // 迭代keys
                while (it.hasNext()) {
                    //Reactor负责dispatch收到的事件
                    SelectionKey sk = it.next();
                    // 通过key，进行IO事件的处理
                    dispatch(sk);
                }
                selected.clear();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void dispatch(SelectionKey sk) {
        // 获取 attach的对象
        Runnable handler = (Runnable) sk.attachment();
        //调用之前attach绑定到选择键的handler处理器对象
        if (handler != null) {
            handler.run();
        }
    }

    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                System.out.println("接收到一个连接");
                if (channel != null) {
                    new EchoHandler(selector, channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }

}
