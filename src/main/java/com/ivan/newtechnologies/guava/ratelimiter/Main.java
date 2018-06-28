package com.ivan.newtechnologies.guava.ratelimiter;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.Uninterruptibles;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static final int LIMIT = 2;

    public static void main(String[] args) {

        final RateLimiter rateLimiter = RateLimiter.create(LIMIT);

        final List<Integer> numbers = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());

        numbers.forEach(i -> {
            rateLimiter.acquire();
            System.out.println("Here we go: " + i + " - " + LocalTime.now());
        });

        System.out.println("Batch acquire...");

        final List<List<Integer>> partition = Lists.partition(numbers, LIMIT);
        partition.forEach(list -> {
            rateLimiter.acquire(LIMIT);
            list.forEach(i -> System.out.println("There we go: " + i + " - " + LocalTime.now()));
        });

        // rate will not be 2/second, instead there will be a big pause between first and second acquire
        rateLimiter.acquire(LIMIT * 5);
        IntStream.range(0, LIMIT * 5).forEach(i -> System.out.println("Here we go: " + i + " - " + LocalTime.now()));
        rateLimiter.acquire(LIMIT * 5);
        IntStream.range(0, LIMIT * 5).forEach(i -> System.out.println("Here we go: " + i + " - " + LocalTime.now()));

        // non-blocking
        numbers.forEach(i -> {
                    if (!rateLimiter.tryAcquire(1, 500, TimeUnit.MILLISECONDS)) {
                        System.out.println("Limit exceeded: " + i);
                        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
                        return;
                    }
                    System.out.println("Here we go: " + i);
                });
    }

}
