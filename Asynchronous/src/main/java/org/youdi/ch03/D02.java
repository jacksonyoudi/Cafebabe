package org.youdi.ch03;

import java.sql.Time;
import java.util.concurrent.*;

public class D02 {
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个compeleteFuture
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        // 2. 开启线程计算任务结果
        POOL_EXECUTOR.execute(
                () -> {
                    //
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("---" + Thread.currentThread().getName() + "------");
                    completableFuture.complete("hello, jiaduo");
                }
        );

        //
        System.out.println("---main thread wait future result----");
        System.out.println(completableFuture.get());
    }
}
