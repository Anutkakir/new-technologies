package com.ivan.newtechnologies.googleanalytics;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.GaData;
import com.google.common.collect.Lists;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    private static final String APPLICATION_NAME = "MyAnalytics";
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final String TABLE_ID = "ga:160016808";

    private static HttpTransport HTTP_TRANSPORT;


    public static void main(String[] args) throws Exception {

        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        final Analytics analytics = initializeAnalytics();

        GaData gaData = executeDataQuery(analytics);

        System.out.println(gaData);
    }

    private static Analytics initializeAnalytics() throws Exception {
        // Authorization.
        Credential credential = authorize();

        // Set up and return Google Analytics API client.
        return new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
                APPLICATION_NAME).build();
    }

    private static Credential authorize() throws Exception {
        final FileInputStream credentialStream = new FileInputStream("c:\\Users\\Ivan_Stankov\\Downloads\\MyTestProject-fd631b277549.json");

        final GoogleCredential googleCredential = GoogleCredential.fromStream(credentialStream)
                .createScoped(Lists.newArrayList(AnalyticsScopes.ANALYTICS_READONLY));
        googleCredential.refreshToken();

        System.out.println(googleCredential.getAccessToken());
        return googleCredential;
    }

    private static GaData executeDataQuery(Analytics analytics) throws IOException {
        return analytics.data().ga().get(TABLE_ID, // Table Id.
                "2012-01-01", // Start date.
                "2012-01-14", // End date.
                "ga:sessions") // Metrics.
                .setDimensions("ga:date")
                .setMaxResults(25)
                .execute();
    }
}
