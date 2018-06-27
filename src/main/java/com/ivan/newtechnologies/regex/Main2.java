package com.ivan.newtechnologies.regex;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {

    public static void main1(String[] args) {
        final String example = "width: 100px; background-image: url(\"/profiles/anoldfriend/photo\");";

        final String regex = "url\\(\"?\'?(.+?)\"?\'?\\)";
        final Pattern pattern = Pattern.compile(regex);

        final Matcher matcher = pattern.matcher(example);

        System.out.println(matcher.find());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        System.out.println(matcher.replaceFirst(String.format("url(\"%s\")", "/asf/asdf")));
    }

    public static void main2(String[] args) throws IOException {
//        final String example = "asdfa afaf asfdf\nasdfasdf :url(\"aa\");url(\"bb\")sdfasdf \n sdfasdf\n";
        final String example = FileUtils.readFileToString(new File("D:\\IDEO\\oie-platform\\impl\\oi2-autotest\\build\\autotest-result\\htmldownload\\static\\resources\\20180213085929\\generated\\default\\ltr\\filtered_1c3sqo64q_components.css"));
//        final String example = "t(transparent,transparent),url(\"https://d1f7u7ix00i08n.cloudfront.net/static/resources/20180213085929/rwd-images/sprites-ltr.svg\"); b";

        final String regex = "(url\\(\"(.+?)\"\\))";
        final Pattern pattern = Pattern.compile(regex);

        final Matcher matcher = pattern.matcher(example);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }

//        System.out.println(matcher.find());

//        for (int i = 0; i < matcher.groupCount(); i++) {
//            if (matcher.find(i)) {
//                System.out.println(matcher.group(i));
//            }
//        }
    }

    public static void main(String[] args) throws URISyntaxException {

//        final URI uri = new URI("resources/static/resources/20180213085929/generated/default/ltr/filtered_1c3sqo64q_components.css");
////        final URI resolve = uri.resolve("../../../rwd-images/timeline/timeline_mask.png");
//        final URI resolve = uri.relativize(new URI("resources/static/rwd-images/20180213085929/rwd-images/sprites-ltr.svg"));
//        System.out.println(resolve);

        final URI uri = new URI("resources/static/rwd-images/20180213085929/rwd-images/sprites-ltr.svg");
        final URI resolve = uri.relativize(new URI("resources/static/resources/20180213085929/generated/default/ltr/filtered_1c3sqo64q_components.css"));
        System.out.println(resolve);
    }

}
