package org.youdi.ch03;

import java.util.concurrent.*;

public class D04 {
    private static final ThreadPoolExecutor bizPoolExecutor = new ThreadPoolExecutor(
            8,
            8,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(10),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("over");
                    }
                },
                bizPoolExecutor
        );

        System.out.println(future.get());
    }
}
