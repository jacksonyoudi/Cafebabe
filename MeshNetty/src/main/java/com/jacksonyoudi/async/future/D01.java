package com.jacksonyoudi.async.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/10/14
 **/
public class D01 {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                new Supplier<String>() {
                    @Override
                    public String get() {
                        return "Hello";
                    }
                }
        );

        future = future.thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + " World";
            }
        })
                .thenApply(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.toUpperCase();
                    }
                });

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        CompletableFuture<String> apply = CompletableFuture.supplyAsync(() -> "Hello").thenApply(
                s -> s + " word"
        ).thenApply(
                String::toUpperCase
        );

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
