package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublicModel {

    @SerializedName("Video")
    @Expose
    private Video_Discover_Model video;
    @SerializedName("User")
    @Expose
    private User user;
    @SerializedName("Sound")
    @Expose
    private Sound sound;

    public Video_Discover_Model getVideo() {
        return video;
    }

    public void setVideo(Video_Discover_Model video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

}
