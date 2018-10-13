package com.example.eshopkashmir.eshopkashmir;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

public class LauncherActivity extends AppCompatActivity implements AdvancedWebView.Listener {

    private static final String WISHLIST = "https://www.eshopkashmir.in/index.php?route=account/wishlist";
    private static final String ORDER = "https://www.eshopkashmir.in/index.php?route=account/order";

    ProgressDialog progressDialog = null;
    AdvancedWebView mWebView;
    public static final String url = "https://www.eshopkashmir.in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        mWebView = (AdvancedWebView) findViewById(R.id.urlWebViewMain);
        mWebView.setListener(this,this);
        mWebView.setGeolocationEnabled(true);
        mWebView.setMixedContentAllowed(true);
        mWebView.setCookiesEnabled(true);
        mWebView.setThirdPartyCookiesEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onReceivedTitle(WebView view, String title) {
                progressDialog.dismiss();
            }

        });

        if(isInternetPresent()){
            mWebView.loadUrl(url);
        } else {
            Toast.makeText(LauncherActivity.this,"Check Your Internet Connection",Toast.LENGTH_LONG).show();
            return;
        }


    }

    @Override
    public void onBackPressed()

    {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
//        mWebView.loadUrl("https://www.eshopkashmir.com");
    }




    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        if (!url.equals(WISHLIST)&& !url.equals(ORDER)) {
            Log.e("URL", url);
            progressDialog = ProgressDialog.show(LauncherActivity.this, getString(R.string.loading), getString(R.string.wait), false, false);
            progressDialog.setCancelable(true);
        }
    }

    @Override
    public void onPageFinished(String url) {
        progressDialog.dismiss();
    }



    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        Toast.makeText(this,"There was some error",Toast.LENGTH_SHORT).show();
        Log.e("error",description);

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }
    @Override
    public void onExternalPageRequest(String url) {

    }

    private boolean isInternetPresent(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return  networkInfo != null && networkInfo.isConnected();
    }

}