package com.jacksonyoudi.callable;

import java.util.concurrent.*;

public class T {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello word";
            }
        };

        ExecutorService pool = Executors.newCachedThreadPool();

        Future<String> future = pool.submit(callable); // 异步
        System.out.println(future.get()); // 阻塞

        pool.shutdown();


    }
}
