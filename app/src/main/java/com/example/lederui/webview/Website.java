package com.example.lederui.webview;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by holyminier on 2017/4/19.
 */

public class Website extends AppCompatActivity{
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private LinearLayout mLlProgress;
    private LinearLayout mLlFail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mLlProgress = (LinearLayout) findViewById(R.id.ll_progress);
        mLlFail = (LinearLayout) findViewById(R.id.ll_fail);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWebView = (WebView) findViewById(R.id.toWeb);
        mWebView.loadUrl("http://" + url);

        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100){
                    mLlProgress.setVisibility(View.INVISIBLE);
                }else {
                    ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivity != null){
                        NetworkInfo info = connectivity.getActiveNetworkInfo();
                        if (info != null && info.isConnected()){
                            if (info.getState() == NetworkInfo.State.CONNECTED){
                                mLlProgress.setVisibility(View.VISIBLE);
                            }
                        }else {
                            mLlFail.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
//        mWebView = new WebView(this);
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        try {
//            mWebView.loadUrl("http://" + url);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        setContentView(mWebView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        mWebView = (WebView) findViewById(R.id.toWeb);
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
