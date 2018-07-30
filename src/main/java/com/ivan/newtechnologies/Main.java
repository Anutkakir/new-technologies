package com.ivan.newtechnologies;

import java.util.Random;

import java.util.regex.Pattern;

public class Main {

    private static final String DOMAIN_PART = "(?:[a-zA-Z0-9_]+)(?:-?[a-zA-Z0-9_]+)*";
    private static final String DOMAIN_NAME = String.format("(?:%1$s)(?:\\.%1$s)", DOMAIN_PART);

    public static void main(String[] args) throws Exception {
        System.out.println(DOMAIN_NAME);

        final Pattern pattern = Pattern.compile(DOMAIN_NAME);

        System.out.println(pattern.matcher("asdf123-123ddfd").matches());

    }

}