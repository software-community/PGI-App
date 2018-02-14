package com.mapps.stroleapp.careathome;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.mapps.stroleapp.R;

import java.util.Locale;

public class DegreeObliquePositionVideoActivity extends AppCompatActivity  {
    //private EasyVideoPlayer player;
    private WebView mWebView;
    private boolean mIsPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_stroke);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        String url = getString(R.string.url_obliquePosition ) ;



        WebSettings ws = mWebView.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.setJavaScriptEnabled(true);

        mIsPaused = true;
        //resumeBrowser();
        //mWebView.loadUrl(media_url);*/
        String html = "<style>\n" +
                ".video-container { \n" +
                "position: relative; \n" +
                "padding-bottom: 56.25%; \n" +
                "padding-top: 35px; \n" +
                "height: 0; \n" +
                "overflow: hidden; \n" +
                "}\n" +
                ".video-container iframe { \n" +
                "position: absolute; \n" +
                "top:0; \n" +
                "left: 0; \n" +
                "width: 100%; \n" +
                "height: 100%; \n" +
                "}\n" +
                "</style>\n" +
                "<div class=\"video-container\">\n" +
                "    <iframe id='iframe' onclick='myfunction()' src=\""+url+"\" allowfullscreen=\" allowfullscreen\" frameborder=\"0\">\n" +
                "    </iframe>\n" +
                "</div>";
        mWebView.loadData(html, "text/html", null);

    }
}

