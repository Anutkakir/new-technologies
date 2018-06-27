package com.ivan.newtechnologies.regex;

import org.apache.commons.lang3.text.StrBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String regex = "From:.*<(.*)>";

        String myString = "Date: Thu, 20 Jul 2017 14:47:01 +0000\n" +
                "From: Test1 OIEngine <noreply-staging@oiengine.com>\n" +
                "Reply-To: noreply@openideo.com\n" +
                "To: Admin Last15Name43 <admin-1500561725688@uiserver.oiengine.com>\n" +
                "Message-ID: <0100015d60775d1e-38799294-0396-481b-b552-3a566e9038d0-000000@email.amazonses.com>\n" +
                "Subject: Test1 OIEngine: Someone commented on your contribution\n" +
                "MIME-Version: 1.0\n" +
                "Content-Type: text/html;charset=UTF-8\n" +
                "Content-Transfer-Encoding: 7bit\n" +
                "X-SES-Outgoing: 2017.07.20-54.240.9.109\n" +
                "Feedback-ID: 1.us-east-1.TI/jfFU7nZqnD4OqtoEWLMD8XXbB2RMgVyznhgHvmeA=:AmazonSES";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(myString);

        final boolean found = matcher.find();
        System.out.println("matcher.matches() = " + found);

        if (found) {
            System.out.println("Whole: " + matcher.group(0));
            System.out.println("Group 1: " + matcher.group(1));
        }

        final String a = new StrBuilder()
                .appendln("Date: Tue, 18 Jul 2017 21:16:00 +0000 (UTC)")
                .appendln("From: Nightly <noreply-nightly@oiengine.com>")
                .appendln("Reply-To: noreply-nightly@oiengine.com")
                .build();

        final Matcher matcher2 = Pattern.compile("From:.*<(.*)>").matcher(a);
        matcher2.find();
        final String group = matcher2.group(1);
        System.out.println(group);
    }
}