package com.ivan.newtechnologies.tenantmanager.model;

import java.util.Map;

public class Tenant {

    private String uid;
    private String name;
    private Map<String, String> credentials;
    private String domain_name;
    private String schema_name;
    private String created;
    private String changed;
    private String status = "READY";
    private String cache_namespace;
    private String cdn_media_endpoint;
    private String cdn_resources_endpoint;
    private String media_storage;
    private String search_index;
    private String sqs_bounce_queue;
    private String sqs_complaint_queue;
    private String group;

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(final Map<String, String> credentials) {
        this.credentials = credentials;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(final String domain_name) {
        this.domain_name = domain_name;
    }

    public String getSchema_name() {
        return schema_name;
    }

    public void setSchema_name(final String schema_name) {
        this.schema_name = schema_name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(final String created) {
        this.created = created;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(final String changed) {
        this.changed = changed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getCache_namespace() {
        return cache_namespace;
    }

    public void setCache_namespace(final String cache_namespace) {
        this.cache_namespace = cache_namespace;
    }

    public String getCdn_media_endpoint() {
        return cdn_media_endpoint;
    }

    public void setCdn_media_endpoint(final String cdn_media_endpoint) {
        this.cdn_media_endpoint = cdn_media_endpoint;
    }

    public String getCdn_resources_endpoint() {
        return cdn_resources_endpoint;
    }

    public void setCdn_resources_endpoint(final String cdn_resources_endpoint) {
        this.cdn_resources_endpoint = cdn_resources_endpoint;
    }

    public String getMedia_storage() {
        return media_storage;
    }

    public void setMedia_storage(final String media_storage) {
        this.media_storage = media_storage;
    }

    public String getSearch_index() {
        return search_index;
    }

    public void setSearch_index(final String search_index) {
        this.search_index = search_index;
    }

    public String getSqs_bounce_queue() {
        return sqs_bounce_queue;
    }

    public void setSqs_bounce_queue(final String sqs_bounce_queue) {
        this.sqs_bounce_queue = sqs_bounce_queue;
    }

    public String getSqs_complaint_queue() {
        return sqs_complaint_queue;
    }

    public void setSqs_complaint_queue(final String sqs_complaint_queue) {
        this.sqs_complaint_queue = sqs_complaint_queue;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(final String group) {
        this.group = group;
    }
}
