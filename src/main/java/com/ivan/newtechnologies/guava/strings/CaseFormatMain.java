package com.ivan.newtechnologies.guava.strings;

import com.google.common.base.CaseFormat;

public class CaseFormatMain {

    public static void main(String[] args) {
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, "oneTwo"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "oneTwo"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "oneTwo"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "oneTwo"));
    }
}
