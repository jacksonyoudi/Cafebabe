package com.jacksonyoudi.handbook.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/09/29
 **/
public class BIOServer {
    public static void main(String[] args) throws Exception {
        //  线程池机制
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket socket = new ServerSocket(6666);
        System.out.println("服务启动了");
        while (true) {
            System.out.println("线程id" + Thread.currentThread().getId() + "名字:" + Thread.currentThread().getName());
            // 监听
            System.out.println("等待l连接");
            final Socket accept = socket.accept();
            System.out.println("连接一个客户端了");

            pool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(accept);
                }
            });
        }

    }

    public static void handler(Socket socket) {
        System.out.println("线程id:" + Thread.currentThread().getId() + "名字:" + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("线程id:" + Thread.currentThread().getId() + "名字:" + Thread.currentThread().getName());
                System.out.println("read");
                int read = inputStream.read(bytes);
                if (-1 != read) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}

