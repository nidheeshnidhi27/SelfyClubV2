
package com.selfyclub.selfyclubappV2.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("video")
    @Expose
    private List<VideoAd> video = null;
    @SerializedName("gif")
    @Expose
    private List<Gif> gif = null;
    @SerializedName("image")
    @Expose
    private List<ImageModel> image = null;

    public List<VideoAd> getVideos() {
        return video;
    }

    public void setVideos(List<VideoAd> video) {
        this.video = video;
    }

    public List<Gif> getGifs() {
        return gif;
    }

    public void setGifs(List<Gif> gif) {
        this.gif = gif;
    }

    public List<ImageModel> getImages() {
        return image;
    }

    public void setImages(List<ImageModel> image) {
        this.image = image;
    }

}
