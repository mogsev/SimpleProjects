package com.mogsev.testapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.testapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.textView1.setOnClickListener((v) -> {
            mBinding.textView1.setSelected(true);
            mBinding.textView2.setSelected(false);
        });

        mBinding.textView2.setOnClickListener((v) -> {
            mBinding.textView2.setSelected(true);
            mBinding.textView1.setSelected(false);
        });

    }
}
