package com.ivan.newtechnologies.apache.poi;

import java.io.InputStream;

public class InputStreams {

    public static InputStream people() {
        return InputStreams.class.getClassLoader().getResourceAsStream("poi/people.xlsx");
    }

}
