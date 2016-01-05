package com.bhmedia.lichvannien2016.ui.http.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Van on 1/24/2015.
 */
public class BaseResponse {
    @SerializedName("errorCode")
    public String errorCode;

    @SerializedName("msg")
    public String msg;
}
