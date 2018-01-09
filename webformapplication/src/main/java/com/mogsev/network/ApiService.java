package com.mogsev.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Eugene Sikaylo on 1/9/2018.
 * email: mogsev@gmail.com
 */

public interface ApiService {

    public static final String BASE_URL = "http://testing.dev.4k.com.ua/";

    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

}
