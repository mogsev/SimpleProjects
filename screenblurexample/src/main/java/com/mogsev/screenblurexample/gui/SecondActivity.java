package com.mogsev.screenblurexample.gui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mogsev.screenblurexample.R;
import com.mogsev.screenblurexample.asynctasks.BlurAsyncTask;
import com.mogsev.screenblurexample.databinding.ActivitySecondBinding;


import timber.log.Timber;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySecondBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        mBinding.buttonTakeScreenshot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Timber.i("onClick");
        switch (v.getId()) {
            case R.id.button_take_screenshot:
                onTakeScreenShot();
                break;
        }
    }

    private void onTakeScreenShot() {
        Timber.i("onTakeScreenShot");

        new BlurAsyncTask(mBinding.mainView);


    }

}
