package com.ivan.newtechnologies.guava.strings;

import com.google.common.base.CharMatcher;

public class CharMatcherMain {

    public static void main(String[] args) {
        System.out.println(CharMatcher.DIGIT.retainFrom("123OneTwoThree"));
        System.out.println(CharMatcher.DIGIT.removeFrom("123OneTwoThree"));
    }
}
