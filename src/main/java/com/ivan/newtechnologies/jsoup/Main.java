package com.ivan.newtechnologies.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    private static final String DATA_DIGEST_ATTR = "data-digest-type";

    public static void main(String[] args) throws Exception {
        final Document document = Jsoup.parse(Main.class.getClassLoader().getResourceAsStream("html/email.eml"), "UTF-8", "");

        document.getElementsByAttribute(DATA_DIGEST_ATTR)
            .forEach(elem -> {
                System.out.println("Digest type: " + elem.attr(DATA_DIGEST_ATTR));
                System.out.println("Element html:\n" + elem.toString());
            });

    }

}
