package com.mogsev.fragmentapp.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.fragmentapp.R;
import com.mogsev.fragmentapp.utils.Logger;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
        setContentView(R.layout.activity_second);
        overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_pop_enter, R.anim.slide_right_pop_exit);
    }
}
