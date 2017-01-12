package com.yohan.dummyapp.rest;


import com.yohan.dummyapp.fragment.ResponseObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Admin on 06-01-17.
 */

public interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("users")
    Call<ResponseObject> sayHi(@Body Model params);
}
