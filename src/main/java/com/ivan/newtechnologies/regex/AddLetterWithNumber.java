package com.ivan.newtechnologies.regex;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddLetterWithNumber {

    public static void main(String[] args) {
        // Input: AA3BBCD4S
        // Output: AAAAABBCDDDDDS

        final String input = "AA3BBCD4S";
        final String output = "AAAAABBCDDDDDS";

        final String actualResult = process(input);

        System.out.println("Actual result - " + actualResult);
        System.out.println("Matches - " + output.equals(actualResult));
    }

    private static String process(final String input) {
        final Pattern pattern = Pattern.compile("(\\w)(\\d+)");
        final Matcher matcher = pattern.matcher(input);

        final StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            final String letter = matcher.group(1);
            final int repeat = Integer.valueOf(matcher.group(2));

            matcher.appendReplacement(result, StringUtils.repeat(letter, repeat + 1));
        }
        matcher.appendTail(result);

        return result.toString();
    }

}
