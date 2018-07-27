package com.ivan.newtechnologies.functionprog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class LazyInitialization {

    public static void main(String[] args) {
        final Function<String, String> func = input -> {
            System.out.println("Very expensive computation goes here...");
            return input.toUpperCase();
        };
        final Function<String, String> cachedFunc = cacheOf(func);

        // not cached invocations
        System.out.println(func.apply("hello"));
        System.out.println(func.apply("world"));
        System.out.println(func.apply("hello"));
        System.out.println(func.apply("world"));

        System.out.println();

        // cached invocations
        System.out.println(cachedFunc.apply("hello"));
        System.out.println(cachedFunc.apply("world"));
        System.out.println(cachedFunc.apply("hello"));
        System.out.println(cachedFunc.apply("world"));
    }

    private static <T, R> Function<T, R> cacheOf(final Function<T, R> toCache) {
        // cache map should be weak in case it is long lived
//         final Map<T, R> cache = Collections.synchronizedMap(new WeakHashMap<>());
        final Map<T, R> cache = new ConcurrentHashMap<>();
        return t -> cache.computeIfAbsent(t, toCache);// multiple threads will load it simultaneously
    }
}
