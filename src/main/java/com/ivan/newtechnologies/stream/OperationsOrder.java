package com.ivan.newtechnologies.stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class OperationsOrder {

    public static void main(String[] args) {
        final List<String> list = Lists.newArrayList("1", "2");

        list.stream()
                .filter(num -> {
                    System.out.println("Filter - " + num);
                    return true;
                })
                .map(num -> {
                    System.out.println("Map - " + num);
                    return num;
                })
                .collect(Collectors.toList());

        // Filter - 1
        // Map - 1
        // Filter - 2
        // Map - 2
    }

}
