package com.ivan.newtechnologies.threads.lock.livelock;

public class Main {

    public static void main(String[] args) {
        final Spouse husband = new Spouse("Bob");
        final Spouse wife = new Spouse("Alice");

        final Spoon s = new Spoon(husband);

        new Thread(() -> husband.eatWith(s, wife)).start();
        new Thread(() -> wife.eatWith(s, husband)).start();
    }

}
