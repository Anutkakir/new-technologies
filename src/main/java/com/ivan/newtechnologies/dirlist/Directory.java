package com.ivan.newtechnologies.dirlist;

public class Directory {

    private final String name;
    private final String size;

    public Directory(final String name, final String size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }
}
