package com.mogsev.fragmentapp.gui.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.fragmentapp.R;
import com.mogsev.fragmentapp.gui.fragments.FirstFragment;
import com.mogsev.fragmentapp.utils.Logger;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.i(TAG, "onCreate");

        Fragment fragment = FirstFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }
}
