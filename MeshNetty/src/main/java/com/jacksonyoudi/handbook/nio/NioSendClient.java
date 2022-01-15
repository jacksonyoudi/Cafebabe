package com.jacksonyoudi.handbook.nio;


import io.netty.resolver.InetSocketAddressResolver;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2022/01/15
 **/
public class NioSendClient {
    private CharSet charSet = Charset.forName("UTF-8");

    /**
     * 向服务器传输文件
     */
    public void sendFile() {
        try {
            String srcPath = "";
            String desPath = "xxx";

            File file = new File(srcPath);
            if (!file.exists()) {
                System.out.println("no exists");
                System.exit(1);
            }
            FileChannel fileChannel = new FileInputStream(file).getChannel();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().connect(new InetSocketAddress("127.0.0.1", 18899));
            socketChannel.configureBlocking(false);

            while (!socketChannel.finishConnect()) {
                // 不断的自旋，等待，或者做一些其他的事情
            }

            ByteBuffer buffer = ByteBuffer.allocate(100);

            // 读数据到 buffer，然后写到 socket中
            while (fileChannel.write(buffer) != -1) {
                buffer.flip();
                socketChannel.read(buffer);
                buffer.clear();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
