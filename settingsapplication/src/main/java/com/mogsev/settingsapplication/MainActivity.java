package com.mogsev.settingsapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mogsev.basecontent.utils.Logger;
import com.mogsev.settingsapplication.business.SettingsBusiness;
import com.mogsev.settingsapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.setSettingsBusiness(new SettingsBusiness());
    }
}
