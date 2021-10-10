package com.jacksonyoudi.handbook.nio.groupchat;

import java.util.Scanner;

public class Client {
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
