package com.ivan.newtechnologies.functionprog;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class Memoization {

    public static void main(String[] args) {
        final Supplier<String> supplier = () -> {
            System.out.println("Very long computation goes here...");
            return "Hello, world!";
        };

        final Supplier<String> lazySupplier = memoize(supplier);

        // original supplier
        System.out.println(supplier.get());
        System.out.println(supplier.get());

        System.out.println();

        // memoized supplier
        System.out.println(lazySupplier.get());
        System.out.println(lazySupplier.get());
    }

    private static <T> Supplier<T> memoize(final Supplier<T> original) {
        final Object dummy = new Object();
        final AtomicReference<Object> ar = new AtomicReference<>();

        return () -> {
            if (ar.weakCompareAndSet(null, dummy)) {
                final T result = original.get();
                ar.set((Supplier<T>) (() -> result));
            }

            while (!(ar.get() instanceof Supplier)) {
                if (ar.get() instanceof RuntimeException) {
                    throw (RuntimeException) ar.get();
                }
            }
            return ((Supplier<T>) ar.get()).get();
        };
    }

}
