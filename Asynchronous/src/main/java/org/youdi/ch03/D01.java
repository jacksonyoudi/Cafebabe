package org.youdi.ch03;


import java.util.concurrent.*;

public class D01 {
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());


    public static String doSomethingA() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- do somethingA-----");

        return "TaskAResult";
    }

    public static String doSomethingB() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- do somethingB-----");

        return "TaskBResult";
    }


    public static void test(String[] args) throws ExecutionException, InterruptedException {

        // 创建futureTask
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            String result = null;
            try {
                result = doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        //  开启异步单元执行任务A
        Thread threadA = new Thread(futureTask, "threadA");
        threadA.start();

        String b = doSomethingB();

        // 同步等待 阻塞
        String s = futureTask.get();

        System.out.println("B:" + b + " A:" + s);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            String result = null;
            try {
                result = doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        // 开启异步单元执行任务A 放到pool中执行
        POOL_EXECUTOR.execute(futureTask);


        Future<String> future = POOL_EXECUTOR.submit(()->{
            String result = null;
            try {
                result = doSomethingB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });


        String b = doSomethingB();

        // 同步等待 阻塞
        String s = futureTask.get();

        System.out.println("B:" + b + " A:" + s);

    }

}
