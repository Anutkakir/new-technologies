package com.ivan.newtechnologies.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapMain {

    public static void main(String[] args) {
        final BiMap<String, Integer> bm = HashBiMap.create();
        bm.put("One", 1);
        bm.put("Two", 2);

        System.out.println(bm);
        System.out.println(bm.get("One"));
        System.out.println(bm.inverse().get(2));
    }

}
