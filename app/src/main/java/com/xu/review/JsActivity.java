package com.xu.review;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Administrator on 2016/6/27.
 */
public class JsActivity extends Activity {
    private WebView wv;
    private static final String LOGTAG = "MainActivity2";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        wv = (WebView) this.findViewById(R.id.wv);
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new JsInteration(), "control");
        wv.setWebChromeClient(new WebChromeClient() {
        });
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                testMethod(wv);
            }
        });
        wv.loadUrl("file:///android_asset/www/test.html");
    }

    private void testMethod(WebView wv) {
        String call = "javascript:sayHello()";
        call = "javascript:alertMessage(\""+"content"+"\")";
//        call = "javascript:toastMessage(\""+"content"+"\")";
//        call = "javascript:sumToJava(1,2)";
        wv.loadUrl(call);
    }


    public class JsInteration {

        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void onSumResult(int result) {
            Log.i(LOGTAG, "onSumResult result=" + result);
        }

    }
}
