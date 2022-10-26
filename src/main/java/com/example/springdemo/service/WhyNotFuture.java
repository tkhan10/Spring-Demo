package com.example.springdemo.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WhyNotFuture {
    /**
     * There are different ways to implement asynchronous programing in java using any of the below mechanisms, for example
     * you can use Futures, ExecutorService, Callback Interfaces, ThreadPool etc
     *
     * 1. It Cannot be completed Manually.
     * 2. Multiple Futures Cannot be chained together.
     * 3. We cannot combine multiple Futures together.
     * 4. No proper exceptional handling mechanism.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<List<Integer>> future = executorService.submit(()->{
            System.out.println("Thread Name - "+Thread.currentThread().getName());
            //TimeUnit.MINUTES.sleep(1);
            System.out.println(10/0);
           return Arrays.asList(1,2,3,4);
        });

        List<Integer> list = future.get();
        System.out.println(list);

    }
}
