package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("country")
	private String country;

	@SerializedName("role")
	private String role;

	@SerializedName("gender")
	private String gender;

	@SerializedName("city")
	private String city;

	@SerializedName("bio")
	private String bio;

	@SerializedName("long")
	private String jsonMemberLong;

	@SerializedName("password")
	private String password;

	@SerializedName("id")
	private String id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	@SerializedName("lat")
	private String lat;

	@SerializedName("website")
	private String website;

	@SerializedName("social")
	private String social;

	@SerializedName("created")
	private String created;

	@SerializedName("ip")
	private String ip;

	@SerializedName("profile_pic")
	private String profilePic;

	@SerializedName("verified")
	private String verified;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("active")
	private String active;

	@SerializedName("version")
	private String version;

	@SerializedName("token")
	private String token;

	@SerializedName("social_id")
	private String socialId;

	@SerializedName("phone")
	private String phone;

	@SerializedName("fb_id")
	private String fbId;

	@SerializedName("dob")
	private String dob;

	@SerializedName("device_token")
	private String deviceToken;

	@SerializedName("PrivacySetting")
	private PrivacySetting privacySetting;

	@SerializedName("online")
	private String online;

	@SerializedName("PushNotification")
	private PushNotification pushNotification;

	@SerializedName("auth_token")
	private String authToken;

	@SerializedName("device")
	private String device;

	@SerializedName("username")
	private String username;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	public String getBio(){
		return bio;
	}

	public void setJsonMemberLong(String jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public String getJsonMemberLong(){
		return jsonMemberLong;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
	}

	public void setSocial(String social){
		this.social = social;
	}

	public String getSocial(){
		return social;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setIp(String ip){
		this.ip = ip;
	}

	public String getIp(){
		return ip;
	}

	public void setProfilePic(String profilePic){
		this.profilePic = profilePic;
	}

	public String getProfilePic(){
		return profilePic;
	}

	public void setVerified(String verified){
		this.verified = verified;
	}

	public String getVerified(){
		return verified;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setActive(String active){
		this.active = active;
	}

	public String getActive(){
		return active;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return version;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setSocialId(String socialId){
		this.socialId = socialId;
	}

	public String getSocialId(){
		return socialId;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setFbId(String fbId){
		this.fbId = fbId;
	}

	public String getFbId(){
		return fbId;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
	}

	public void setDeviceToken(String deviceToken){
		this.deviceToken = deviceToken;
	}

	public String getDeviceToken(){
		return deviceToken;
	}

	public void setPrivacySetting(PrivacySetting privacySetting){
		this.privacySetting = privacySetting;
	}

	public PrivacySetting getPrivacySetting(){
		return privacySetting;
	}

	public void setOnline(String online){
		this.online = online;
	}

	public String getOnline(){
		return online;
	}

	public void setPushNotification(PushNotification pushNotification){
		this.pushNotification = pushNotification;
	}

	public PushNotification getPushNotification(){
		return pushNotification;
	}

	public void setAuthToken(String authToken){
		this.authToken = authToken;
	}

	public String getAuthToken(){
		return authToken;
	}

	public void setDevice(String device){
		this.device = device;
	}

	public String getDevice(){
		return device;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}