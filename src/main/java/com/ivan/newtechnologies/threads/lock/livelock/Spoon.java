package com.ivan.newtechnologies.threads.lock.livelock;

public class Spoon {

    private Spouse owner;

    public Spoon(Spouse d) {
        this.owner = d;
    }

    public Spouse getOwner() {
        return this.owner;
    }

    public synchronized void setOwner(Spouse d) {
        this.owner = d;
    }

    public synchronized void use() {
        System.out.printf("%s has eaten!", this.owner.getName());
    }

}
