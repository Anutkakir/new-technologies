package com.ivan.newtechnologies.gson;

import java.io.Serializable;

/**
 * Created by Ivan_Stankov on 23.06.2016.
 */
public class A implements Serializable {

    private int i;

    public A(final int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
