package org.youdi.ch03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class D03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 默认是使用jvm中唯一的ForkJoinPool.commonPool()线程池来执行异步任务的
        // 允许显式指定使用线程池
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("over");
            }
        });

        System.out.println(runAsync.get());

    }


}
