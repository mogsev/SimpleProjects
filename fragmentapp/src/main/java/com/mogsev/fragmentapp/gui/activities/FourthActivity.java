package com.mogsev.fragmentapp.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.fragmentapp.R;
import com.mogsev.fragmentapp.utils.Logger;

public class FourthActivity extends AppCompatActivity {
    private static final String TAG = FourthActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
        setContentView(R.layout.activity_fourth);
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit);
    }
}
