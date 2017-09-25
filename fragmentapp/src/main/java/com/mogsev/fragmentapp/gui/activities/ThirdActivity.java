package com.mogsev.fragmentapp.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.fragmentapp.R;
import com.mogsev.fragmentapp.utils.Logger;

public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = ThirdActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
        setContentView(R.layout.activity_third);
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Logger.i(TAG, "onBackPressed");
        overridePendingTransition(R.anim.slide_left_pop_enter, R.anim.slide_left_pop_exit);
    }
}
