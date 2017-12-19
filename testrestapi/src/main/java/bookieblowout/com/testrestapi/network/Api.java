package bookieblowout.com.testrestapi.network;

import java.util.concurrent.TimeUnit;

import bookieblowout.com.testrestapi.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eugene Sikaylo on 12/19/2017.
 * email: mogsev@gmail.com
 */

public class Api {

    private Retrofit.Builder mRetrofitBuilder;
    private OkHttpClient.Builder mHttpClientBuilder;
    private ApiService mApiService;

    public Api() {
        // create http client builder
        mHttpClientBuilder = new OkHttpClient.Builder();
        mHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        // follow redirect
        mHttpClientBuilder.followRedirects(true)
                .followSslRedirects(true);

        // Create Http logging interceptor
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            mHttpClientBuilder.addInterceptor(logging);
        }

        // create instance retrofit builder
        mRetrofitBuilder = new Retrofit.Builder();
        mRetrofitBuilder.baseUrl("http://thor.dev.4k.com.ua/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        mRetrofitBuilder.client(mHttpClientBuilder.build());

        Retrofit retrofit = mRetrofitBuilder.build();

        // create Api service
        mApiService = retrofit.create(ApiService.class);


    }

    public ApiService getApiService() {
        return mApiService;
    }

}
