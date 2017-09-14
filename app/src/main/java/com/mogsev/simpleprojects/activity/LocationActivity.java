package com.mogsev.simpleprojects.activity;

import android.databinding.DataBindingUtil;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.simpleprojects.R;
import com.mogsev.simpleprojects.databinding.ActivityLocationBinding;

public class LocationActivity extends AppCompatActivity {
    private static final String TAG = LocationActivity.class.getSimpleName();

    private ActivityLocationBinding mBinding;
    private LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_location);

        //GoogleApiClient.Builder apiClientBuilder = new GoogleApiClient.Builder(ctx);

    }
}
