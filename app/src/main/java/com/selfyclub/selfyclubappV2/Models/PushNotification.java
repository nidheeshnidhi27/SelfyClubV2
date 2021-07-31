package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.SerializedName;

public class PushNotification{

	@SerializedName("comments")
	private String comments;

	@SerializedName("video_updates")
	private String videoUpdates;

	@SerializedName("mentions")
	private String mentions;

	@SerializedName("id")
	private String id;

	@SerializedName("new_followers")
	private String newFollowers;

	@SerializedName("direct_messages")
	private String directMessages;

	@SerializedName("likes")
	private String likes;

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getComments(){
		return comments;
	}

	public void setVideoUpdates(String videoUpdates){
		this.videoUpdates = videoUpdates;
	}

	public String getVideoUpdates(){
		return videoUpdates;
	}

	public void setMentions(String mentions){
		this.mentions = mentions;
	}

	public String getMentions(){
		return mentions;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNewFollowers(String newFollowers){
		this.newFollowers = newFollowers;
	}

	public String getNewFollowers(){
		return newFollowers;
	}

	public void setDirectMessages(String directMessages){
		this.directMessages = directMessages;
	}

	public String getDirectMessages(){
		return directMessages;
	}

	public void setLikes(String likes){
		this.likes = likes;
	}

	public String getLikes(){
		return likes;
	}
}