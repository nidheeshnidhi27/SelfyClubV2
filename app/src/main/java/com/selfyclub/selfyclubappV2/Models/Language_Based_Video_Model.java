package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.SerializedName;

public class Language_Based_Video_Model{

	@SerializedName("msg")
	private MsgLang msg;

	@SerializedName("code")
	private int code;

	public void setMsg(MsgLang msg){
		this.msg = msg;
	}

	public MsgLang getMsg(){
		return msg;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}
}