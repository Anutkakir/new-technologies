package com.ivan.newtechnologies.elasticsearch;

import com.ivan.newtechnologies.elasticsearch.model.Tweet;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ivan_Stankov on 16.02.2016.
 */
public class Main {

    public static void main(String[] args) {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200")
                .multiThreaded(true)
                .build());
        JestClient jestClient = factory.getObject();

        getArticle(jestClient);
    }

    private static void getArticle(JestClient client) {
        Get get = new Get.Builder("articles", "1").type("tweet").build();
        try {
            DocumentResult execute = client.execute(get);
            Tweet tweet = execute.getSourceAsObject(Tweet.class);
            System.out.println(tweet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchArticle(JestClient client) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());

        Search search = new Search.Builder(builder.toString())
                .addIndex("articles")
                .addType("tweet")
                .build();

        try {
            SearchResult result = client.execute(search);
            List<Tweet> tweets = result.getSourceAsObjectList(Tweet.class);
            System.out.println(tweets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createIndex(JestClient jestClient) {
        try {
            jestClient.execute(new CreateIndex.Builder("articles").build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDocument(JestClient client) {
        try {
            Tweet tweet = new Tweet("1", "sport", "Basketball is a very fair sport");

            Index index = new Index.Builder(tweet).index("articles").type("tweet").build();
            client.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
