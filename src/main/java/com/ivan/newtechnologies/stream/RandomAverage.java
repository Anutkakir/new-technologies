package com.ivan.newtechnologies.stream;

import java.util.Random;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class RandomAverage {

    public static void main(String[] args) {
        final long[] votes = new Random(42).longs(1000).toArray();

        System.out.println(LongStream.of(votes).average().getAsDouble());
        System.out.println(LongStream.of(votes).asDoubleStream().average().getAsDouble());
        System.out.println(LongStream.of(votes).asDoubleStream().parallel().average().getAsDouble());

        double total = 0.0;
        for (long vote : votes) {
            total += vote;
        }
        System.out.println(total / votes.length);

        long total1 = 0L;
        for (long vote : votes) {
            total1 += vote;
        }
        System.out.println(total1 / votes.length);
    }
}
