package com.mogsev.fragmentapp.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.fragmentapp.R;

import timber.log.Timber;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
        setContentView(R.layout.activity_second);
        overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_pop_enter, R.anim.slide_right_pop_exit);
    }
}
