package com.bhmedia.lichvannien2016.ui.http.request;

import com.bhmedia.lichvannien2016.ui.common.Constant;
import com.bhmedia.lichvannien2016.ui.http.response.GetWeatherResponse;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Van on 1/24/2015.
 */
public class RequestApi {
    static RequestApi mInstance;
    private RequestService mService;

    private RequestApi(RequestService service) {
        mService = service;
    }

    public static RequestApi getInstance() {
        if (mInstance == null) {
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(5, TimeUnit.MINUTES);
            RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(client)).setEndpoint(
                    Constant.HOST_API).build();
            RequestService service = restAdapter.create(RequestService.class);
            mInstance = new RequestApi(service);
        }
        return mInstance;
    }

    public void weather(String key,
                        String feedkey,
                        String format,
                        String q,
                        String includeLocation,
                        String num_of_days,
                        String fx,
                        String extra,
                        Callback<GetWeatherResponse> callback) {
        mService.weather(key, feedkey, format, q, includeLocation, num_of_days, fx, extra, callback);
       
    }
    public void weather1(Callback<GetWeatherResponse> callback) {
        mService.weather1(callback);
    }
}
