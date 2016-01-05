package com.bhmedia.lichvannien2016.ui.http.request;

import com.google.gson.annotations.SerializedName;

public class BaseRequest {
	@SerializedName("errorCode")
    public String errorCode;

    @SerializedName("msg")
    public String msg;
}
