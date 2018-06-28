package com.ivan.newtechnologies.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class BlockingRead {

    public static void main(String[] args) {

        try {
            final StringBuilder stringBuilder = new StringBuilder();

            final File file = new File("D:\\Learning\\miscellaneous\\src\\main\\resources\\files\\names.txt");
            final FileChannel c = new FileInputStream(file).getChannel();
//            FileChannel c = FileChannel.open(Paths.get("D:\\Learning\\miscellaneous\\src\\main\\resources\\files\\names.txt"));



            final ByteBuffer b = ByteBuffer.allocate(1);
            int i = 0;

            while (c.read(b) != -1) {

                final String readString = new String(b.array(), StandardCharsets.UTF_8);
                System.out.println(++i + ": " + readString);
                stringBuilder.append(readString);

                b.compact();
            }

            System.out.println(stringBuilder.toString());
            System.out.println(file.toPath().toAbsolutePath());

            c.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
