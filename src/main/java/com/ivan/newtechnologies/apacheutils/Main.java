package com.ivan.newtechnologies.apacheutils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("(.+)@(\\w+)\\.(\\w+)");

    public static void main(String[] args) {
        final String s = "irina205@g.com";

        System.out.println(uglify(s));
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
            e.printStackTrace();
            return "*****************";
        }
    }

    private static String replaceLastSymbols(final String input) {
        if (input.length() == 2) {
            return "**";
        }

        return input.substring(0, 2) + StringUtils.repeat("*", input.length() - 2);
    }

    private static String replaceFirstSymbols(final String input) {
        if (input.length() == 2) {
            return "**";
        }

        return StringUtils.repeat("*", input.length() - 2) + input.substring(input.length() - 2, input.length());
    }
}
