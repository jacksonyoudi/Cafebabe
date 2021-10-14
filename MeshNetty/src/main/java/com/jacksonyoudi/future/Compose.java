package com.jacksonyoudi.future;

import com.jacksonyoudi.utils.SmallTool;

import java.util.concurrent.CompletableFuture;

public class Compose {
    public static void main(String[] args) {
        applyAsync();


        System.out.println("---------------------------");

        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");


        // map
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> {
                    SmallTool.printTimeAndThread("厨师炒菜");
                    SmallTool.sleepMillis(1200);
                    return "番茄炒蛋";
                }
                // 等待上一个完成
        ).thenCompose(
                dish -> CompletableFuture.supplyAsync(
                        () -> {
                            SmallTool.printTimeAndThread("服务员打饭");
                            SmallTool.sleepMillis(100);
                            return dish + "+ 米饭";
                        }
                )
        );

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s 好了,小白开吃", future.join()));

    }


    private static void applyAsync() {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");


        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> {
                    SmallTool.printTimeAndThread("厨师炒菜");
                    CompletableFuture<String> race = CompletableFuture.supplyAsync(() -> {
                        SmallTool.printTimeAndThread("服务员打饭");
                        SmallTool.sleepMillis(100);
                        return " + 米饭";
                    });
                    SmallTool.sleepMillis(1200);
                    return "番茄炒蛋" + race.join();
                }
        );


        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s 好了,小白开吃", future.join()));


    }
}
