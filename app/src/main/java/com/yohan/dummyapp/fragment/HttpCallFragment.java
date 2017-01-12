package com.yohan.dummyapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yohan.dummyapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import utils.CommonUtils;
import utils.UtilsResponseInterface;

/**
 * Created by Admin on 04-01-17.
 */

public class HttpCallFragment extends Fragment implements UtilsResponseInterface {
    private static final String URL_STRING_HI = "http://requestb.in/u3tgrhu3";
    private static final String URL_STRING_POST = "https://jsonplaceholder.typicode.com/posts/";
    private String postNo;

    @BindView(R.id.textViewResponse)
    TextView tv_response;
    @OnClick(R.id.buttonSimpleRequest)
    public void sayHi()
    {
        MyNetworkCall call = new MyNetworkCall();
        call.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, URL_STRING_HI);
    }
    @OnClick(R.id.buttonJSONRequest)
    public void jsonReq()
    {
        CommonUtils.showInputDialog(getActivity(), "Enter Post number", this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_http_call, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResponse(String response) {
        postNo= response;
        MyNetworkCall call = new MyNetworkCall();
        call.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, URL_STRING_POST+postNo);
    }

        protected boolean isOnline()
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo!=null && networkInfo.isConnectedOrConnecting());
        }
    private class MyNetworkCall extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (isOnline()) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Processing Request");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();
            } else {
                CommonUtils.showErrorDialog(getActivity(), "No network connectivity!.\nPlease enable Internet");
                cancel(true);
            }
        }

        @Override
        protected String doInBackground(String... url) {
            try {
                OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build();
                Request request = new Request.Builder().url(url[0]).build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful())
                    return response.body().string();
                if (response.code()==404)
                    return "Target not found \n404";
                                /*URL url = new URL(strings[0]);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setDoInput(true);
                                connection.setRequestMethod("GET");
                                connection.setConnectTimeout(5000);
                                connection.connect();
                                InputStream stream;

                                int status = connection.getResponseCode();

                                if (status != HttpURLConnection.HTTP_OK) {
                                    Log.d("Network Error", connection.getErrorStream().toString());
                                    CommonUtils.showErrorDialog(getActivity(), "404 error");
                                    return "404";
                                   *//*     stream = connection.getErrorStream();
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                                        String tr;
                                        while ((tr = reader.readLine()) != null )
                                        {
                                            Log.i("exc",reader.readLine());
                                        }*//*
                                }
                                else {
                                    stream = connection.getInputStream();
                                }

                                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                                StringBuilder stringBuilder = new StringBuilder();
                                String line;
                                while((line=reader.readLine())!=null)
                                    stringBuilder.append(line).append("\n");
                                responseStr = stringBuilder.toString();
                                return responseStr;
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return responseStr;
                            */
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Call Failed";

        }

        @Override
        protected void onPostExecute(String responseStr) {
            if(isCancelled())
                tv_response.setText("Cancelled");
            else
                tv_response.setText(responseStr);
            progressDialog.dismiss();
            super.onPostExecute(responseStr);
        }
    }
}
