package com.ivan.newtechnologies.guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class MultisetMain {

    public static void main(final String[] args) {
        final Multiset<String> ms = HashMultiset.create();

        ms.add("one");
        ms.add("one");
        ms.add("one");
        ms.add("two");

        System.out.println("Count of 'one' - " + ms.count("one"));
        System.out.println("Element set - " + ms.elementSet());

        ms.forEach(System.out::println);
    }

}
