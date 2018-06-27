package com.ivan.newtechnologies.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExHelper {

    public static String extractCommentId(String link) {
        return getFirstMatch("(?<=(/comments#!)).+", link);
    }

    public static Integer extractScores(String string) {
        return Integer.parseInt(getFirstMatch("\\d+", string));
    }

    public static String getFirstMatch(String regex, String text) {
        return getFirstMatch(getMatcher(regex, text));
    }

    public static String getSecondMatch(String regex, String text) {
        return getSecondMatch(getMatcher(regex, text));
    }

    /**
     * @param hexColor #FFF or #FFFFFF
     * @return rgb(255, 255, 255) or rgb() if there was wrong parameter
     */
    public static String convertColorFromHexToRgb(String hexColor) {
        StringBuilder result = new StringBuilder();
        result.append("rgb(");
        String commaAndSpaceStr = ", ";
        if (hexColor.length() == 4) {
            for (int i = 1; i < 4; i++) {
                String value = hexColor.substring(i, i + 1) + hexColor.substring(i, i + 1);
                result.append(Integer.parseInt(value, 16));
                if (i < 3) {
                    result.append(commaAndSpaceStr);
                }
            }
        } else if (hexColor.length() == 7) {
            for (int i = 1; i < 7; i += 2) {
                String value = hexColor.substring(i, i + 2);
                result.append(Integer.parseInt(value, 16));
                if (i < 5)
                result.append(commaAndSpaceStr);
            }
        }
        result.append(")");
        return result.toString();
    }

    public static String getChallengeNameFromUrl(String challengeUrl) {
        String url = getFirstMatch("(?<=(challenge/)).+", challengeUrl);
        String[] challengeWords = url.split("-");
        StringBuilder challengeName = new StringBuilder();
        for (String challengeWord : challengeWords) {
            challengeName.append(challengeWord).append(' ');
        }
        return challengeName.toString().trim();
    }

    public static String extractRgbBannerTextColor(String bannerTextColor) {
        return getFirstMatch("rgb\\([\\w]+, [\\w]+, [\\w]+\\)", bannerTextColor);
    }

    public static String extractEmail(String email) {
        return getFirstMatch("[\\w\\.-]+@([\\w-]+\\.)+[\\w-]+", email);
    }

    private static String getFirstMatch(Matcher matcher) {
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private static String getSecondMatch(Matcher matcher) {
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static Matcher getMatcher(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text);
    }
}
