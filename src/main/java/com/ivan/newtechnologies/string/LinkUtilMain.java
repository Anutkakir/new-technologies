package com.ivan.newtechnologies.string;

public class LinkUtilMain {

    public static void main(String[] args) throws Exception {

        final String result = LinkUtil.activeLinks(  "asf..com",                               "https://aaa.com");
        final String result2 = LinkUtil.activeLinks( "asfasdfasdf",                            "https://aaa.com");
        final String result3 = LinkUtil.activeLinks( "google.com",                             "https://aaa.com");
        final String result4 = LinkUtil.activeLinks( "http://google.com",                      "https://aaa.com");
        final String result5 = LinkUtil.activeLinks( "http://google..com",                     "https://aaa.com");
        final String result6 = LinkUtil.activeLinks( "https://google.com",                     "https://aaa.com");
        final String result7 = LinkUtil.activeLinks( "ftp://google.com",                       "https://aaa.com");
        final String result8 = LinkUtil.activeLinks( "https://abc.qwerty.com",                 "https://aaa.com");
        final String result9 = LinkUtil.activeLinks( "https://.qwerty.com",                    "https://aaa.com");
        final String result10 = LinkUtil.activeLinks("https://-qwerty.com",                    "https://aaa.com");
        final String result11 = LinkUtil.activeLinks("https://qwerty-.com",                    "https://aaa.com");
        final String result12 = LinkUtil.activeLinks("https://qwerty--.com",                   "https://aaa.com");
        final String result13 = LinkUtil.activeLinks("https://qwerty-abc.com",                 "https://aaa.com");
        final String result14 = LinkUtil.activeLinks("https://qwerty-abc.john.com",            "https://aaa.com");
        final String result15 = LinkUtil.activeLinks("https://qwerty.john-abc.com",            "https://aaa.com");

        final String result45 = LinkUtil.activeLinks("mail@example.com", "https://aaa.com");

        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
        System.out.println(result7);
        System.out.println(result8);
        System.out.println(result9);
        System.out.println(result10);
        System.out.println(result11);
        System.out.println(result13);
        System.out.println(result14);
        System.out.println(result15);

        System.out.println(result45);
    }
}
