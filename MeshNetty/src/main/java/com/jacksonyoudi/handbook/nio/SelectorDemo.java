package com.jacksonyoudi.handbook.nio;

import com.sun.corba.se.impl.ior.JIDLObjectKeyTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2022/01/15
 **/
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);

        channel.bind(new InetSocketAddress(1899));

        //注册到选择器的通道必须处于非阻塞模式下，否则将抛出IllegalBlockingModeException异常。
        channel.register(selector, SelectionKey.OP_ACCEPT);

        // 而socket相关的所有通道都可以。其次，一个通道并不一定支持所有的四种IO事件。例如，服务器监听通道ServerSocketChannel仅支持Accept（接收到新连接）IO事件，而传输通道SocketChannel则不同，它不支持Accept类型的IO事件。
        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // 监听有新链接
                } else if (key.isConnectable()) {
                    // IO事件： 传输通道链接成功
                } else if (key.isReadable()) {
                    // IO事件，传输通道可读
                } else if (key.isWritable()) {
                    // IO事件， 传输通道可写
                }
                // 处理完成后，移除选择键
                // 处理完成后，需要将选择键从SelectionKey集合中移除，以防止下一次循环时被重复处理
                iterator.remove();
            }
        }

    }
}
