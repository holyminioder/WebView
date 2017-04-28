package com.example.lederui.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEtUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtUrl = (EditText) findViewById(R.id.et_url);
    }

    public void onBtnClick(View view){
        //url : "http://www.baidu.com"
        String url = mEtUrl.getText().toString();
        Intent intent = new Intent(MainActivity.this, Website.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
