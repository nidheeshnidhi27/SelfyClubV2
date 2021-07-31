package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video_Discover_Model {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fb_id")
    @Expose
    private String fbId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("thum")
    @Expose
    private String thum;
    @SerializedName("gif")
    @Expose
    private String gif;
    @SerializedName("view")
    @Expose
    private String view;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("sound_id")
    @Expose
    private String soundId;
    @SerializedName("privacy_type")
    @Expose
    private String privacyType;
    @SerializedName("allow_comments")
    @Expose
    private String allowComments;
    @SerializedName("allow_duet")
    @Expose
    private String allowDuet;
    @SerializedName("block")
    @Expose
    private String block;
    @SerializedName("duet_video_id")
    @Expose
    private String duetVideoId;
    @SerializedName("old_video_id")
    @Expose
    private String oldVideoId;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("like")
    @Expose
    private Integer like;
    @SerializedName("favourite")
    @Expose
    private Integer favourite;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("like_count")
    @Expose
    private Integer likeCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getThum() {
        return thum;
    }

    public void setThum(String thum) {
        this.thum = thum;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSoundId() {
        return soundId;
    }

    public void setSoundId(String soundId) {
        this.soundId = soundId;
    }

    public String getPrivacyType() {
        return privacyType;
    }

    public void setPrivacyType(String privacyType) {
        this.privacyType = privacyType;
    }

    public String getAllowComments() {
        return allowComments;
    }

    public void setAllowComments(String allowComments) {
        this.allowComments = allowComments;
    }

    public String getAllowDuet() {
        return allowDuet;
    }

    public void setAllowDuet(String allowDuet) {
        this.allowDuet = allowDuet;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getDuetVideoId() {
        return duetVideoId;
    }

    public void setDuetVideoId(String duetVideoId) {
        this.duetVideoId = duetVideoId;
    }

    public String getOldVideoId() {
        return oldVideoId;
    }

    public void setOldVideoId(String oldVideoId) {
        this.oldVideoId = oldVideoId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getFavourite() {
        return favourite;
    }

    public void setFavourite(Integer favourite) {
        this.favourite = favourite;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

}
