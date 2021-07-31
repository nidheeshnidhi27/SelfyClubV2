package com.selfyclub.selfyclubappV2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contest_Get_Model implements Serializable {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("msg")
    @Expose
    private CMsg msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CMsg getMsg() {
        return msg;
    }

    public void setMsg(CMsg msg) {
        this.msg = msg;
    }


}
