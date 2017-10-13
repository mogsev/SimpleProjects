package com.mogsev.fragmentapp.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.fragmentapp.R;

import timber.log.Timber;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
        setContentView(R.layout.activity_fourth);
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit);
    }
}
