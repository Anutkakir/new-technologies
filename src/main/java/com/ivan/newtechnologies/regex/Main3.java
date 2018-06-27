package com.ivan.newtechnologies.regex;

import java.util.regex.Pattern;

public class Main3 {

    public static void main(String[] args) {
        final String example = "qwerty-12345";

        final Pattern pattern = Pattern.compile("[\\p{Alnum}-]+", Pattern.UNICODE_CHARACTER_CLASS);
        System.out.println(pattern.matcher(example).matches());
    }

}
