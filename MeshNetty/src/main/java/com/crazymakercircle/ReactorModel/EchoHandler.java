package com.crazymakercircle.ReactorModel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class EchoHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECIEVING = 0, SENDING = 1;
    int state = RECIEVING;


    public EchoHandler(Selector selector, SocketChannel channel) throws IOException {
        this.channel = channel;
        channel.configureBlocking(false);
        // 仅仅取得选择键，后设置感兴趣的IO事件
        sk = channel.register(selector, SelectionKey.OP_READ);

        // 将Handler作为选择键的附件
        sk.attach(this);

        //  第二步 注册read就绪事件
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        // 状态机
        try {
            if (state == SENDING) {

                channel.write(byteBuffer);

                byteBuffer.clear();

                sk.interestOps(SelectionKey.OP_READ);

                state = RECIEVING;

            } else if (state == RECIEVING) {
                int length = 0;
                while ((length = channel.read(byteBuffer)) > 0) {
                    System.out.println(new String(byteBuffer.array(), 0, length));
                }
                // 读完
                byteBuffer.flip();

                // 读完后，注册write就绪事件
                sk.interestOps(SelectionKey.OP_WRITE);

                state = SENDING;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sk.cancel();

            try {
                channel.finishConnect();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
