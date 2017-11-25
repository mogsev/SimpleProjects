package com.mogsev.screenblurexample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mogsev.screenblurexample.databinding.ActivityMainBinding;
import com.mogsev.screenblurexample.gui.SecondActivity;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.buttonSecondActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Timber.i("onClick");

        switch (v.getId()) {
            case R.id.button_second_activity:
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }

}
