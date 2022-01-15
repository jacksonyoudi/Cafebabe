package com.jacksonyoudi.handbook.nio.reactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2022/01/15
 **/
public class ConnectionPerThread implements Runnable {
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(19988);
            while (!Thread.interrupted()) {
                Socket accept = serverSocket.accept();
                Handler handler = new Handler(accept);
                new Thread(handler).start();
            }


        } catch (Exception e) {

        }
    }


}

class Handler implements Runnable {
    final Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] bytes = new byte[1024];
                int read = socket.getInputStream().read(bytes);
                socket.getOutputStream().write("hello".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
