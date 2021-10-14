package com.jacksonyoudi.async.future;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/10/14
 **/
public class Two {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        Future<String> future = service.submit(() -> {
                    System.out.println("running...");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "return task";
                }
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("do something else");

        try {
            System.out.println(future.get());  //等待 future 的执行结果，执行完毕之后打印出来
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}

// 比起future.get()，其实更推荐使用get (long timeout, TimeUnit unit) 方法，设置了超时时间可以防止程序无限制的等待future的结果。
// Future虽然可以实现获取异步执行结果的需求，但是它没有提供通知的机制，我们无法得知Future什么时候完成。
//
//要么使用阻塞，在future.get()的地方等待future返回的结果，这时又变成同步操作。要么使用isDone()轮询地判断Future是否完成，这样会耗费CPU的资源。
//
//
//