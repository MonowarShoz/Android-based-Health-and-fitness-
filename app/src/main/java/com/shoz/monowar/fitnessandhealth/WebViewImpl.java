package com.shoz.monowar.fitnessandhealth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewImpl extends WebViewClient {
    private Activity activity = null;

    public WebViewImpl(Activity activity){
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.indexOf("https://whatscookingamerica.net/NutritionalChart.htm")>-1) return false;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url) );
        activity.startActivity(intent);
        return true;
    }
}
