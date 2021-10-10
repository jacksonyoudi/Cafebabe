package com.jacksonyoudi.handbook.nio.groupchat;

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
 * @date: 2021/09/30
 **/
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 6667;


    public GroupChatServer() {
        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 监听
    public void listen() throws Exception {
        while (true) {
            int count = selector.select();
            if (count > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress() + "上线了");
                    }

                    if (key.isReadable()) {
                        // 处理读
                        readData(key);
                    }

                    iterator.remove();
                }


            } else {
                System.out.println("等待中.....");
            }
        }
    }


    // 读取客户端消息
    private void readData(SelectionKey key) {
        SocketChannel channel = null;

        try {
            channel = (SocketChannel) key.channel();
            channel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int read = channel.read(buffer);
            if (read > 0) {
                String msg = new String(buffer.array());

                System.out.println("from " + channel.getRemoteAddress() + ":" + msg);
                sendMessageToClient(msg, key);

            }

        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + "离线了");
                // 取消ß
                key.cancel();
                channel.close();
            } catch (Exception a) {
                a.printStackTrace();
            }
        }


    }

    // 转发消息

    private void sendMessageToClient(String msg, SelectionKey self) {
        System.out.println("服务器转发消息");

        Set<SelectionKey> selectionKeys = selector.keys();

        for (SelectionKey key : selectionKeys) {
            if (key != self) {
                System.out.println("自己");
                continue;
            }

            Channel channelO = key.channel();
            if (channelO instanceof ServerSocketChannel) {
                continue;
            }
            SocketChannel channel = (SocketChannel) channelO;

            try {
                channel.configureBlocking(false);
            } catch (Exception a) {
                a.printStackTrace();
            }
            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            try {
                channel.write(buffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    public static void main(String[] args) throws Exception {
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}
