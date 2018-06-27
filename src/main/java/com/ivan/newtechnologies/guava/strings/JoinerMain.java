package com.ivan.newtechnologies.guava.strings;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

public class JoinerMain {

    public static void main(String[] args) {
        Joiner joiner = Joiner.on(" ").skipNulls();
        System.out.println(joiner.join("This", null, "is", "Sparta!"));

        joiner = Joiner.on(" ").useForNull("...");
        System.out.println(joiner.join("This", null, "is", "Sparta!"));

        final Joiner.MapJoiner mapJoiner = Joiner.on(" ").withKeyValueSeparator(" - ");
        System.out.println(mapJoiner.join(ImmutableMap.<String, Integer>builder()
                .put("One", 1)
                .put("Two", 2)
                .build()));
    }
}
