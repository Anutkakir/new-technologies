package com.ivan.newtechnologies.elasticsearch.model;

import io.searchbox.annotations.JestId;

/**
 * Created by Ivan_Stankov on 16.02.2016.
 */
public class Tweet {

    @JestId
    private String id;
    private String topic;
    private String message;

    public Tweet(String id, String topic, String message) {
        this.id = id;
        this.topic = topic;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
