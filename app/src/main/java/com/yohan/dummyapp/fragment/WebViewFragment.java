package com.yohan.dummyapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yohan.dummyapp.R;
import com.yohan.dummyapp.activity.NavigationActivityMain;

/**
 * Created by Admin on 22-12-16.
 */

public class WebViewFragment extends Fragment {
    private WebView webView;
    private ProgressBar pb_web;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        ((NavigationActivityMain) this.getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.web));

        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) view.findViewById(R.id.swipeWeb);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                webView.loadUrl(webView.getUrl());

            }
        });
        pb_web = (ProgressBar) view.findViewById(R.id.progressBar_web);
        webView = (WebView) view.findViewById(R.id.web_view_dummy);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb_web.setProgress(newProgress);
                if(newProgress>0)
                    pb_web.setVisibility(View.VISIBLE);
                if(newProgress==100)
                {
                    swipeView.setRefreshing(false);
                    pb_web.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl("http://www.google.com/");

        return view;
    }


}
