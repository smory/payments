package pl.onet.sympatia.payments;

/**
 * Created by Rafał on 2018-02-14.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        Intent i = getIntent();
        String url= i.getStringExtra("url");
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        CookieSyncManager.createInstance(getApplicationContext());
        CookieSyncManager.getInstance().startSync();
        CookieManager.getInstance().setAcceptCookie(true);

        String cookieString = "access_token=aaaa;";

        CookieManager c = CookieManager.getInstance();
        Log.v("Build: ",String.valueOf(Build.VERSION.SDK_INT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            c.setCookie("https://payments.sympatia.onet.pl", cookieString);
            Log.v("Cookies: ", c.getCookie("https://payments.sympatia.onet.pl"));
        }
        ActionBar actionBar = (ActionBar)getApplicationContext().getSupportActionBar();

        webView.loadUrl(url);
    }

}