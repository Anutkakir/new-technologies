package com.ivan.newtechnologies.collection.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SublistBeforeStreamCreation {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");

        list = list.subList(0, 2);

        Stream<String> stream = list.stream();

        list.add("four");

        stream = stream.map(s -> s + "!!!");

        list.add("five");

        stream.forEach(System.out::println);
    }

}
