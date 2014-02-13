package com.example.localform;

import android.util.Log;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
 
import android.telephony.TelephonyManager;

public class MainActivity extends Activity {
	
	private String IMEI;
	private String PhoneNumber;

    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        IMEI = tm.getDeviceId();
        PhoneNumber = tm.getLine1Number();
        
        Log.d("IMEI", IMEI);
        Log.d("PhoneNumber", PhoneNumber);

        webView = (WebView) findViewById(R.id.web_view);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new AndroidBridge(this), "android");

        webView.loadUrl("file:///android_asset/form.html");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
