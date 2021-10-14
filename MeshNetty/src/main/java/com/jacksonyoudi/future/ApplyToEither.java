package com.jacksonyoudi.future;

import com.jacksonyoudi.utils.SmallTool;

import java.util.concurrent.CompletableFuture;

public class ApplyToEither {
    public static void main(String[] args) {
        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700路公交正在赶来");
            SmallTool.sleepMillis(100);
            return "700路到了";
        }).applyToEither(
                CompletableFuture.supplyAsync(
                        () -> {
                            SmallTool.printTimeAndThread("800路公交正在赶来");
                            SmallTool.sleepMillis(200);
                            return "800路到了";
                        }
                ), firstComeBus -> firstComeBus
        );

        SmallTool.printTimeAndThread(String.format("%s,小白坐车回家", bus.join()));
    }
}
