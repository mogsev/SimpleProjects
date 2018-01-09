package com.mogsev;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogsev.databinding.ActivityOfflineWebFormBinding;
import com.mogsev.network.model.FieldItem;
import com.mogsev.services.DownloadService;

import java.lang.reflect.Type;
import java.util.List;

import timber.log.Timber;

public class OfflineWebFormActivity extends AppCompatActivity {

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private ActivityOfflineWebFormBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_offline_web_form);

        // download files
        downloadFiles();
        // initialize
        init();

        // show form
        showForm();
    }

    private void init() {
        Timber.i("init");
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
        mBinding.webView.addJavascriptInterface(this, "JSAR");
    }

    private void downloadFiles() {
        Timber.i("downloadFiles");
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_FILE_NAME, "index.html");
        startService(intent);

        intent.putExtra(DownloadService.BUNDLE_FILE_NAME, "bootstrap.min.css");
        startService(intent);

        intent.putExtra(DownloadService.BUNDLE_FILE_NAME, "first.js");
        startService(intent);
    }

    private void showForm() {
        Timber.i("showForm");
        verifyStoragePermissions(this);
        mBinding.mainLayout.postDelayed(() -> openWebForm(), 5000);
    }

    private void openWebForm() {
        Timber.i("openWebForm");
        final String url = String.format("file:///%s%s", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/index.html");
        Timber.i("url: %s", url);
        mBinding.webView.loadUrl(url);
    }


    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permissionWrite = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionRead != PackageManager.PERMISSION_GRANTED || permissionWrite != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @JavascriptInterface
    public void takeFormResult(String result) {
        Timber.i("takeFormResult: %s", result);

        Type itemsListType = new TypeToken<List<FieldItem>>() {}.getType();
        List<FieldItem> list = new Gson().fromJson(result, itemsListType);

        Toast.makeText(this, String.format("Result: %s", list), Toast.LENGTH_SHORT).show();
    }

}
