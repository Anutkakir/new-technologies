package com.ivan.newtechnologies.apache.http;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        try (final CloseableHttpClient httpClient = HttpClients.custom()
                        .setRedirectStrategy(LaxRedirectStrategy.INSTANCE)
                        .build()) {

            final File image = httpClient.execute(new HttpGet("http://localhost:8080/file"),
                    response -> {
                        System.out.println("Response status: " + response.getStatusLine().getStatusCode());
                        final InputStream content = response.getEntity().getContent();

                        Arrays.stream(response.getAllHeaders())
                                .forEach(header -> System.out.println("Header: " + header.getName() + ":" + header.getValue()));

                        final File destination = new File("D:\\Learning\\miscellaneous\\src\\main\\resources\\image.jpg");
                        FileUtils.copyInputStreamToFile(content, destination);
                        return destination;
                    });

        } catch (final Exception e) {
            e.printStackTrace();
        }


    }

}
