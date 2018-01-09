package com.mogsev;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mogsev.databinding.ActivityAssetsWebFormBinding;

import timber.log.Timber;

public class AssetsWebFormActivity extends AppCompatActivity {

    private ActivityAssetsWebFormBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_assets_web_form);

        mBinding.swipeContainer.setOnRefreshListener(() -> {
            Timber.i("mSwipeRefreshLayout");
            mBinding.webView.reload();
        });

        mBinding.webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int progress) {
                Timber.i("onProgressChanged");
                mBinding.swipeContainer.setRefreshing(progress == 100 ? false : true);
            }
        });

        mBinding.webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Timber.i("onPageStarted");
                mBinding.swipeContainer.setRefreshing(true);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Timber.i("onReceivedError");
                if (mBinding != null && mBinding.swipeContainer != null) {
                    mBinding.swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Timber.i("onPageFinished");
                mBinding.swipeContainer.setRefreshing(false);
            }
        });

        mBinding.webView.getSettings().setJavaScriptEnabled(true);

        // open url
        openWebForm();
    }

    private void openWebForm() {
        Timber.i("openWebForm");
        String url = "file:///android_asset/webForms/testForm.html";
        mBinding.webView.loadUrl(url);
        //mBinding.webView.loadUrl("http://testing.dev.4k.com.ua/index.html");
    }

}
