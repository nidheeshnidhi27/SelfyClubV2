package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.SerializedName;

public class Sound{

	@SerializedName("duration")
	private String duration;

	@SerializedName("uploaded_by")
	private String uploadedBy;

	@SerializedName("sound_section_id")
	private String soundSectionId;

	@SerializedName("created")
	private String created;

	@SerializedName("publish")
	private String publish;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("thum")
	private String thum;

	@SerializedName("id")
	private String id;

	@SerializedName("audio")
	private String audio;

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setUploadedBy(String uploadedBy){
		this.uploadedBy = uploadedBy;
	}

	public String getUploadedBy(){
		return uploadedBy;
	}

	public void setSoundSectionId(String soundSectionId){
		this.soundSectionId = soundSectionId;
	}

	public String getSoundSectionId(){
		return soundSectionId;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setPublish(String publish){
		this.publish = publish;
	}

	public String getPublish(){
		return publish;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setThum(String thum){
		this.thum = thum;
	}

	public String getThum(){
		return thum;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAudio(String audio){
		this.audio = audio;
	}

	public String getAudio(){
		return audio;
	}
}