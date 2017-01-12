package com.yohan.dummyapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaControllerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;
import com.yohan.dummyapp.R;
import com.yohan.dummyapp.model.Comment;
import com.yohan.dummyapp.rest.ApiInterface;
import com.yohan.dummyapp.rest.Model;
import com.yohan.dummyapp.rest.MyRetrofitClient;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import utils.CommonUtils;

/**
 * Created by Admin on 06-01-17.
 */

public class RetrofitFragment extends Fragment {
    private Activity parentActivity;
    private View view;
    private Retrofit retrofit;
    private ApiInterface apiInterface;
    private Call<ResponseObject> call;
    @OnClick(R.id.button_retrofit_request)
    public void makeRetrofitRequest(){
        retrofit= MyRetrofitClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);
        call = apiInterface.sayHi(new Model("Yohan"));
        call.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if(response.body()!=null) {
                    ResponseObject responseObject = (ResponseObject) response.body();

                    CommonUtils.showErrorDialog(parentActivity, responseObject.getContent());
                }
                else
                    CommonUtils.showErrorDialog(parentActivity, "Response body is null");
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                CommonUtils.showErrorDialog(parentActivity, "Error");
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_retrofit, container, false);
        parentActivity = getActivity();
        ButterKnife.bind(this, view);
        return view;
    }
}
