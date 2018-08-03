package com.ivan.newtechnologies;

import com.google.common.net.UrlEscapers;

import java.net.URLEncoder;
import java.util.regex.Pattern;

public class Main {

    private static final String DOMAIN_PART = "(?:[a-zA-Z0-9_]+)(?:-?[a-zA-Z0-9_]+)*";
    private static final String DOMAIN_NAME = String.format("(?:%1$s)(?:\\.%1$s)", DOMAIN_PART);

    public static void main(String[] args) throws Exception {
        System.out.println(DOMAIN_NAME);

        final Pattern pattern = Pattern.compile(DOMAIN_NAME);

        System.out.println(pattern.matcher("asdf123-123ddfd").matches());

        System.out.println(URLEncoder.encode("-._~!$'()*,;&=@:+/? ", "UTF-8"));
        System.out.println(UrlEscapers.urlFormParameterEscaper().escape("-._~!$'()*,;&=@:+/? "));
        System.out.println(UrlEscapers.urlFragmentEscaper().escape("-._~!$'()*,;&=@:+/? "));
        System.out.println(UrlEscapers.urlPathSegmentEscaper().escape("-._~!$'()*,;&=@:+/? "));
    }

}