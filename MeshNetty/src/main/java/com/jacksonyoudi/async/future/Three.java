package com.jacksonyoudi.async.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/10/14
 **/
public class Three {
    public static void main(String[] args) throws ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("CompletableFuture");

    }
}
