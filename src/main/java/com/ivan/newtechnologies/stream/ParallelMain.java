package com.ivan.newtechnologies.stream;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ParallelMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long firstNum = 1;
        long lastNum = 100;

        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());

        ForkJoinPool customThreadPool = new ForkJoinPool(2);
        long actualTotal = customThreadPool.submit(() ->
                aList.parallelStream()
                        .peek(i -> System.out.println(Thread.currentThread().getName() + ": " + i))
                        .reduce(0L, Long::sum))
                .get();

        System.out.println((lastNum + firstNum) * lastNum / 2);
        System.out.println(actualTotal);
    }
}
