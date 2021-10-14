package com.jacksonyoudi.future;

import com.jacksonyoudi.utils.SmallTool;

import java.util.concurrent.CompletableFuture;

public class Start {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");


        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(
                () -> {
                    SmallTool.printTimeAndThread("厨师炒菜");
                    SmallTool.sleepMillis(200);
                    SmallTool.printTimeAndThread("厨师打饭");
                    return "番茄炒蛋 + 米饭 做好了";
                }
        );

        SmallTool.printTimeAndThread("小白打王者");
        SmallTool.printTimeAndThread(String.format("%s, 小白开吃", cf1.join()));

    }
}
