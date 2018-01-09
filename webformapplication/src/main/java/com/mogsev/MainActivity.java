package com.mogsev;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.mogsev.databinding.ActivityMainBinding;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // initialize
        init();
    }

    @Override
    public void onClick(View v) {
        Timber.i("onClick");
        switch (v.getId()) {
            case R.id.button_offline_web:
                openOfflineWeb();
                break;
            case R.id.button_assets_web:
                openAssetsWeb();
                break;
        }
    }

    private void init() {
        Timber.i("init");
        mBinding.buttonAssetsWeb.setOnClickListener(this);
        mBinding.buttonOfflineWeb.setOnClickListener(this);
    }

    private void openOfflineWeb() {
        Timber.i("openOfflineWeb");
        Intent intent = new Intent(this, OfflineWebFormActivity.class);
        startActivity(intent);
    }

    private void openAssetsWeb() {
        Timber.i("openAssetsWeb");
        Intent intent = new Intent(this, AssetsWebFormActivity.class);
        startActivity(intent);
    }

}
