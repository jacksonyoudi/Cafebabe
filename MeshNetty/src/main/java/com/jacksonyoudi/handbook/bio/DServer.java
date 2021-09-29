package com.jacksonyoudi.handbook.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DServer {
    public static void main(String[] args) throws Exception {
        // 创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();

        // 如果有连接，就一个线程进行处理
        ServerSocket socket = new ServerSocket(6666);

        System.out.println("服务器启动");

        while (true) {
            Socket accept = socket.accept();
            System.out.println("一个客户端连接过来了");

            pool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(accept);
                }
            });
        }
    }


    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];

        try {
            InputStream inputStream = socket.getInputStream();

            while (true) {

                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭连接");

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

}
