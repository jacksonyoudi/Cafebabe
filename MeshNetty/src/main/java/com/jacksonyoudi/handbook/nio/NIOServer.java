package com.jacksonyoudi.handbook.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();


        // 得到一个selector对象
        Selector selector = Selector.open();

        // 绑定socket, 监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);


        // serversocketchannel注册到selector，关心事件为 op_accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端链接

        while (true) {

            // 没有事件发生
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1s,无连接");
                continue;
            }
            // 如果返回不是0,获取selectionKeys的集合，关注事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();

                // op_accept
                if (key.isAcceptable()) {
                    // 给客户端生成socketchannel
                    SocketChannel accept = serverSocketChannel.accept();

                    System.out.println("一个客户端链接:" + accept.hashCode());
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()) {
                    // 通过key反向获取channel
                    SocketChannel channel = (SocketChannel) key.channel();

                    // 获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();

                    channel.read(buffer);
                    System.out.println("for 客户端" + new String(buffer.array()));
                }

                // 手动从集合中移除当前的key
                iterator.remove();
            }


        }


        //得到一个socketchannel


    }
}
