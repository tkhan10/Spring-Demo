package com.example.springdemo.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 1. What is runAsync() and supplyAsync() method
 */
public class CompletableFutureClassDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.get();
        completableFuture.complete("return some values");
        System.out.println(completableFuture.get());
    }
}
