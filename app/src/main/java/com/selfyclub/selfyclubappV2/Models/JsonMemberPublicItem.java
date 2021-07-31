package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.SerializedName;

public class JsonMemberPublicItem{

	@SerializedName("User")
	private User user;

//	@SerializedName("Video")
//	private Video video;

	@SerializedName("Sound")
	private Sound sound;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

//	public void setVideo(Video video){
//		this.video = video;
//	}
//
//	public Video getVideo(){
//		return video;
//	}

	public void setSound(Sound sound){
		this.sound = sound;
	}

	public Sound getSound(){
		return sound;
	}
}