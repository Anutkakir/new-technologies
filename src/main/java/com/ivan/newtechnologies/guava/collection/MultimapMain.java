package com.ivan.newtechnologies.guava.collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MultimapMain {

    public static void main(String[] args) {
        final Multimap<Integer, String> mm = ArrayListMultimap.create();
        mm.put(1, "one");
        mm.put(1, "один");
        mm.put(1, "one");

        mm.put(2, "two");
        mm.put(2, "два");

        mm.put(3, "three");
        mm.put(3, "три");

        System.out.println("Size: " + mm.size());
        System.out.println("get(1): " + mm.get(1));

        mm.get(4).add("four");
        System.out.println(mm);
    }

}
