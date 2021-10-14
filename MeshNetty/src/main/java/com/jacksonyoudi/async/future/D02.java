package com.jacksonyoudi.async.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/10/14
 **/
public class D02 {
//    public static void main(String[] args) {
//        CompletableFuture<Double> future = CompletableFuture.supplyAsync(
//                () -> "10"
//        ).thenApply(Integer::parseInt).thenApply(i -> i * 10.0);
//
//
//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//        CompletableFuture.supplyAsync(
//                () -> "10"
//        ).thenCompose(
//                new Function<String, CompletionStage<? extends Object>>() {
//                    @Override
//                    public CompletionStage<? extends Object> apply(String s) {
//                        return null;
//                    }
//                }
//
//        )
//    }
}
