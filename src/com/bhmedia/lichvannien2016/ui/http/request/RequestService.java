package com.bhmedia.lichvannien2016.ui.http.request;

import com.bhmedia.lichvannien2016.ui.common.Constant;
import com.bhmedia.lichvannien2016.ui.http.response.GetWeatherResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Van on 1/24/2015.
 */
public interface RequestService {
    @GET(Constant.URL_GET_WEATHER)
    void weather(@Query("key") String key,
                 @Query("feedkey") String feedkey,
                 @Query("format") String format,
                 @Query("q") String q,
                 @Query("includeLocation") String includeLocation,
                 @Query("num_of_days") String num_of_days,
                 @Query("fx") String fx,
                 @Query("extra") String extra,
                 Callback<GetWeatherResponse> callback);

    /*@GET(Constant.URL_GET_WEATHER)
    void weather(Callback<GetWeatherResponse> callback);*/

    @POST(Constant.URL_GET_WEATHER)
    void weather1(Callback<GetWeatherResponse> callback);
}
