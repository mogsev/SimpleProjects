package com.mogsev.network;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Eugene Sikaylo on 1/9/2018.
 * email: mogsev@gmail.com
 */

public class Api {

    @NonNull
    public static final ApiService API_SERVICE = new Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .client(new OkHttpClient())
            .build()
            .create(ApiService.class);

}
