package com.ivan.newtechnologies.stream;

import java.util.stream.Stream;

public class Fibonacci {

    public static void main(String[] args) {
        fib(5);
        System.out.println();
        fibFunctional(5);
    }

    public static void fib(final int n) {
        int x = 0;
        int y = 1;
        int z = 1;

        System.out.print(y + " " + z + " ");
        for (int i = 0; i < n; i++) {
            x = y;
            y = z;
            z = x + y;

            System.out.print(z);
            System.out.print(" ");
        }
    }

    public static void fibFunctional(final int n) {
        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(n + 2)
                .peek(arr -> System.out.print(arr[1]))
                .forEach(arr -> System.out.print(" "));

    }

}
