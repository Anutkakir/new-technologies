package com.ivan.newtechnologies.apache.timedsemaphore;

import org.apache.commons.lang3.concurrent.TimedSemaphore;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        final List<Integer> numbers = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());

        final TimedSemaphore timedSemaphore = new TimedSemaphore(1, TimeUnit.SECONDS, 2);

        numbers.forEach(i -> {
            try {
                timedSemaphore.acquire();
                System.out.println("Here we go: " + i + " - " + LocalTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timedSemaphore.shutdown();
    }

}
