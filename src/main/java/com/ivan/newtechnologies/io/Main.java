package com.ivan.newtechnologies.io;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedInputStream stream = new BufferedInputStream(Main.class.getClassLoader().getResourceAsStream("files/names.txt"));

        final byte[] bytes = new byte[2];

        int bytesRead = 0;
        while (stream.read(bytes, 0, 2) > 0) {
            System.out.println(new String(bytes));
        }
    }

}
