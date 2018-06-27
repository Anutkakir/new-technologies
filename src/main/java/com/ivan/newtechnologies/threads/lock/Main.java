package com.ivan.newtechnologies.threads.lock;

public class Main {

    public static void main(String[] args) {
        final String email = "ivan.stankov2@oiengine.com";
        final String username = email.substring(0, email.indexOf("@"));

        System.out.println(username);
    }
}
