package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contest {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("hashtags")
    @Expose
    private String hashtags;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("publish")
    @Expose
    private String publish;
    @SerializedName("createddate")
    @Expose
    private String createddate;
    @SerializedName("expiry")
    @Expose
    private String expiry;
    @SerializedName("enabledstatus")
    @Expose
    private String enabledstatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getEnabledstatus() {
        return enabledstatus;
    }

    public void setEnabledstatus(String enabledstatus) {
        this.enabledstatus = enabledstatus;
    }

}