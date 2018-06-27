package com.ivan.newtechnologies.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlMain {

    public static void main(String[] args) {
        System.out.println(extractUrl("https://localhost/admin/reports/custom/test_users.json?year=2017"));
        System.out.println(extractUrl("/admin/reports/custom/test_users.json?year=2017"));
        System.out.println(extractUrl("https://localhost/admin/reports/custom/test_users.json"));
        System.out.println(extractUrl("https://localhost/admin/reports/custom/test_users.json?"));
    }

    private static String extractUrl(final String url) {
        final Pattern pattern = Pattern.compile(".*/admin/reports/custom/(.+)\\.json.*");
        final Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
