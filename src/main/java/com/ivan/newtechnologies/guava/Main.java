package com.ivan.newtechnologies.guava;

import com.google.common.net.InternetDomainName;

public class Main {

    public static void main(String[] args) {
        InternetDomainName owner = InternetDomainName.from("mail.google.com").topPrivateDomain();
        System.out.println(owner);
    }
}
