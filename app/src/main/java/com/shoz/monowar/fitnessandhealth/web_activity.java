package com.shoz.monowar.fitnessandhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class web_activity extends AppCompatActivity {

    WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_activity);

       this.webView = (WebView)findViewById(R.id.shoWeb);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewImpl webViewClient = new WebViewImpl(this);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl("https://whatscookingamerica.net/NutritionalChart.htm");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()){
            this.webView.canGoBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
