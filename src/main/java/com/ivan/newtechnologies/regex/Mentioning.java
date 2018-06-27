package com.ivan.newtechnologies.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mentioning {

    private static final Pattern SAVED_MENTION_PATTERN = Pattern.compile("<([T|U|C])(.*?)><(.*?)>");

    public static void main(String[] args) {
        final String comment = "<Uuser2><Firstname Lastname> <Uuser1><Firstname Lastname> Lololo";
        final String result = changeMentionedUserToText(comment, "user1", "DeletedUser");
        System.out.println(result);
    }

    public static String changeMentionedUserToText(final String commentMessage, final String username, final String substitution) {
        final Matcher matcher = SAVED_MENTION_PATTERN.matcher(commentMessage);
        final StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            final String shortType = matcher.group(1);
            final String matcherUsername = matcher.group(2);
            final String fullName = matcher.group(3);

            if ("U".equals(shortType) && matcherUsername.equals(username)) {
                matcher.appendReplacement(sb, substitution);
            }
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
}
