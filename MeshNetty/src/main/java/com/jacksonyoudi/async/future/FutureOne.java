package com.jacksonyoudi.async.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/10/12
 **/
public class FutureOne {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(Integer value) {
        return executor.submit(() -> {
            System.out.println("Calculating..." + value);
            Thread.sleep(1000);
            return value * value;
        });
    }

    public static void main(String[] args) {
        FutureOne futureOne = new FutureOne();
        Future<Integer> calculate = futureOne.calculate(20);



    }
}

