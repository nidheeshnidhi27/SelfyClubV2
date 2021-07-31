package com.selfyclub.selfyclubappV2.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MsgLang {

	@SerializedName("public")
	@Expose
	private List<PublicModel> _public = null;
	@SerializedName("private")
	@Expose
	private PrivateModel _private;

	public List<PublicModel> getPublic() {
		return _public;
	}

	public void setPublic(List<PublicModel> _public) {
		this._public = _public;
	}

	public PrivateModel getPrivate() {
		return _private;
	}

	public void setPrivate(PrivateModel _private) {
		this._private = _private;
	}

}