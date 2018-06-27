package com.ivan.newtechnologies.console;

import java.io.Console;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws URISyntaxException {

        final String[] params = {"a", "ab", "abc"};

        final String reduce = Arrays.stream(params)
                .filter(s -> {
                    System.out.println("First filter: " + s);
                    return s.contains("a");
                })
                .filter(s -> {
                    System.out.println("Second filter: " + s);
                    return s.contains("b");
                })
                .filter(s -> {
                    System.out.println("Third filter: " + s);
                    return s.contains("c");
                })
                .findFirst()
                .orElse(null);


        System.out.println(reduce);

        // System.console() returns null if run not in console
        // compile class and run from base package (where com resides) java com.ivan.newtechnologies.Main
        Console c = System.console();
        for (int i = 0; i <= 100; i++) {
            c.writer().write("\r" + i + "% completed");
            c.writer().flush();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
