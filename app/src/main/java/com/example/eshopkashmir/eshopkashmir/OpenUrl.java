package com.example.eshopkashmir.eshopkashmir;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

public class OpenUrl extends BaseActivity implements AdvancedWebView.Listener {

    private static final String URL_ACCOUNT = "http://eshopkashmir.com/customer/account/login/";
    ProgressDialog progressDialog;
    AdvancedWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        progressDialog = new ProgressDialog();
        setContentView(R.layout.activity_open_url);
        String url = getIntent().getExtras().getString("url");
        mWebView = (AdvancedWebView) findViewById(R.id.urlWebView);
        mWebView.setListener(this,this);
        mWebView.setGeolocationEnabled(true);
        mWebView.setMixedContentAllowed(true);
        mWebView.setCookiesEnabled(true);
        mWebView.setThirdPartyCookiesEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){


            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
                progressDialog.dismiss();

            }

        });
        if(isInternetPresent()){
            mWebView.loadUrl(url);
        } else {
            Toast.makeText(OpenUrl.this,"Check Your Internet Connection",Toast.LENGTH_LONG).show();
            return;
        }

    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        if (mWebView.canGoBack()){
//            mWebView.goBack();
//        }
//    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        if (!url.equals(URL_ACCOUNT)) {
            progressDialog = ProgressDialog.show(OpenUrl.this, "Loading", "Please Wait", false, false);
        } else {
//            Toast.makeText(OpenUrl.this,"Loading Account Page",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPageFinished(String url) {
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
