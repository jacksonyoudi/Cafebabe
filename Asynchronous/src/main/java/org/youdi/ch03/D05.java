package org.youdi.ch03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class D05 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> onetask = CompletableFuture.supplyAsync(
                new Supplier<String>() {
                    @Override
                    public String get() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return "hello";
                    }
                }
        );

        // 在 furture上加事件

        CompletableFuture<Void> over = onetask.thenRun(
                () -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("over");
                }
        );

        System.out.println(over.get());

    }
}
