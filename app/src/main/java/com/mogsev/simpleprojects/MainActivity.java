package com.mogsev.simpleprojects;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mogsev.simpleprojects.fragment.FirstFragment;
import com.mogsev.simpleprojects.model.User;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FirstFragment fragment = FirstFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(null)
                    .commit();
        }
    }

}
