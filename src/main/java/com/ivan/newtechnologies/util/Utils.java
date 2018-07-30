package com.ivan.newtechnologies.util;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Utils {

    public static void sleepQuietly(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String gzipAndBase64(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);

        byte[] buffer = new byte[1024];
        int count = inputStream.read(buffer, 0, 1024);
        while (count > 0) {
            gzipOutputStream.write(buffer, 0, count);
            count = inputStream.read(buffer, 0, 1024);
        }
        gzipOutputStream.close();

        String encoded = DatatypeConverter.printBase64Binary(outputStream.toByteArray());

        return encoded;
    }

    public static String base64AndGzip(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }

        str = DatatypeConverter.printBase64Binary(str.getBytes("UTF-8"));

        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);

        byte[] buffer = new byte[1024];
        int count = inputStream.read(buffer, 0, 1024);
        while (count > 0) {
            gzipOutputStream.write(buffer, 0, count);
            count = inputStream.read(buffer, 0, 1024);
        }
        gzipOutputStream.close();

        return outputStream.toString("UTF-8");
    }
}
