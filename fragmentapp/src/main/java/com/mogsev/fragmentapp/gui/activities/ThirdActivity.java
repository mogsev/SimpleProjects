package com.mogsev.fragmentapp.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.fragmentapp.R;

import timber.log.Timber;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
        setContentView(R.layout.activity_third);
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Timber.i("onBackPressed");
        overridePendingTransition(R.anim.slide_left_pop_enter, R.anim.slide_left_pop_exit);
    }
}
