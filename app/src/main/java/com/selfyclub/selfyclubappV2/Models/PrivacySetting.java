package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.SerializedName;

public class PrivacySetting{

	@SerializedName("liked_videos")
	private String likedVideos;

	@SerializedName("videos_download")
	private String videosDownload;

	@SerializedName("video_comment")
	private String videoComment;

	@SerializedName("id")
	private String id;

	@SerializedName("direct_message")
	private String directMessage;

	@SerializedName("duet")
	private String duet;

	public void setLikedVideos(String likedVideos){
		this.likedVideos = likedVideos;
	}

	public String getLikedVideos(){
		return likedVideos;
	}

	public void setVideosDownload(String videosDownload){
		this.videosDownload = videosDownload;
	}

	public String getVideosDownload(){
		return videosDownload;
	}

	public void setVideoComment(String videoComment){
		this.videoComment = videoComment;
	}

	public String getVideoComment(){
		return videoComment;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDirectMessage(String directMessage){
		this.directMessage = directMessage;
	}

	public String getDirectMessage(){
		return directMessage;
	}

	public void setDuet(String duet){
		this.duet = duet;
	}

	public String getDuet(){
		return duet;
	}
}