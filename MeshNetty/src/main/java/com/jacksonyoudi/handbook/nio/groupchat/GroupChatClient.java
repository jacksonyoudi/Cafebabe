package com.jacksonyoudi.handbook.nio.groupchat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class GroupChatClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public GroupChatClient() throws Exception {
        selector = Selector.open();

        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));

        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_READ);

        userName = socketChannel.getLocalAddress().toString().substring(1);

        System.out.println(userName + "is ok");

    }


    //向服务发送消息

    public void SendMessage(String info) {
        String msg = userName + " say: " + info;

        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ReadMessage() {
        try {
            int c = selector.select();
            if (c > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();

                        ByteBuffer bf = ByteBuffer.allocate(1024);

                        channel.read(bf);

                        System.out.println(new String(bf.array()));

                    }
                }

            }
//            else {
//                System.out.println("没有可用的通道");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        GroupChatClient client = new GroupChatClient();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    client.ReadMessage();
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();


        // 发送数据

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            client.SendMessage(msg);
        }


    }

}
