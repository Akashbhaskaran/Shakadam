package com.example.akashbhaskaran.car;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

public class Video_view extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
       webView.setWebViewClient(new WebViewClient());
        String video = getIntent().getExtras().getString("video");
        //webView.getSettings().setPluginsEnabled(true);
        webView.loadUrl("http://www.shakadam.esy.es/car/"+video);


    }
}
