package com.ivan.newtechnologies.regex;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUglifier {
    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("([^@\\s]+?)@((?:\\w+)(?:\\.\\w+)*)\\.(\\w+)");
//    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("(.+)@((?:\w+)(?:\.\w+)*)\.(\w+)");

    public static void main(String[] args) {
        final String text = "lolo lalala us@first.second.com -> ivan@dfdf.com qwerty hello world";

        final String uglified = uglifyInString(text);

        System.out.println(uglified);
    }

    public static String uglify(final String email) {
        if (email == null) {
            return null;
        }

        if (StringUtils.isBlank(email)) {
            return StringUtils.EMPTY;
        }

        try {
            final Matcher matcher = EMAIL_ADDRESS_PATTERN.matcher(email);
            if (!matcher.matches()) {
                return email;
            }

            final String localPart = matcher.group(1);
            final String mailBox = matcher.group(2);
            final String domain = matcher.group(3);

            return String.format("%s@%s.%s", replaceLastSymbols(localPart), replaceFirstSymbols(mailBox), domain);
        } catch (final Exception e) {
            return "*****************";
        }
    }

    public static String uglifyInString(final String text) {
        if (text == null) {
            return null;
        }

        if (StringUtils.isBlank(text)) {
            return StringUtils.EMPTY;
        }

        final StringBuffer stringBuffer = new StringBuffer();

        final Matcher matcher = EMAIL_ADDRESS_PATTERN.matcher(text);
        while (matcher.find()) {
            final String uglified = uglify(matcher.group());
            matcher.appendReplacement(stringBuffer, uglified);
        }

        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static String replaceLastSymbols(final String input) {
        if (input.length() <= 2) {
            return StringUtils.repeat("*", input.length());
        }

        return input.substring(0, 2) + StringUtils.repeat("*", input.length() - 2);
    }

    private static String replaceFirstSymbols(final String input) {
        if (input.length() <= 2) {
            return StringUtils.repeat("*", input.length());
        }

        return StringUtils.repeat("*", input.length() - 2) + input.substring(input.length() - 2);
    }
}
