package com.ivan.newtechnologies.guava.strings;

import com.google.common.base.Splitter;

import java.util.Map;
import java.util.regex.Pattern;

public class SplitterMain {

    public static void main(String[] args) {

        final String input = ",This,,is,Sparta  ,";

        Iterable<String> split = Splitter.on(",")
                .split(input);
        System.out.println(split);

        split = Splitter.on(",")
                .omitEmptyStrings()
                .split(input);
        System.out.println(split);

        split = Splitter.on(",")
                .omitEmptyStrings()
                .trimResults()
                .split(input);
        System.out.println(split);

        final String mapInput = "1->one 2->two\n3->three";
        final Map<String, String> mapSplit = Splitter.on(Pattern.compile("[\\s\n]"))
                .omitEmptyStrings()
                .withKeyValueSeparator("->")
                .split(mapInput);
        System.out.println(mapSplit);
    }
}
