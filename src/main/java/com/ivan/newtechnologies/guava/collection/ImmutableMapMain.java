package com.ivan.newtechnologies.guava.collection;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ImmutableMapMain {

    public static void main(String[] args) {
        final Map<Integer, String> map = ImmutableMap.<Integer, String>builder()
                .put(1, "one")
                //
                .put(2, "two")
                //
                .put(3, "three")
                .build();

        System.out.println(map.get(1));
        System.out.println(map.get(100));
    }

}
