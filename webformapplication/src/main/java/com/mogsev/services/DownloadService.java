package com.mogsev.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.widget.Toast;

import com.mogsev.network.Api;
import com.mogsev.network.ApiService;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;

import okhttp3.ResponseBody;
import retrofit2.Call;
import timber.log.Timber;

/**
 * Created by Eugene Sikaylo on 1/9/2018.
 * email: mogsev@gmail.com
 */

public class DownloadService extends IntentService {

    public static final String BUNDLE_FILE_NAME = "file_name";

    // first is name, second is url
    private final Queue<Pair<String, String>> mQueue;

    public DownloadService() {
        super("DownloadIntentService");
        mQueue = new LinkedList<>();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Timber.i("onStartCommand");
        String fileName = intent.getStringExtra(BUNDLE_FILE_NAME);
        String url = ApiService.BASE_URL + fileName;
        mQueue.add(new Pair<>(fileName, url));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Timber.i("onHandleIntent");
        final Pair<String, String> pair = mQueue.poll();

        downloadUrl(pair.first, pair.second);
    }

    private void downloadUrl(final String fileName, final String url) {
        Timber.i("downloadUrl fileName: %s, url: %s", fileName, url);

        Call<ResponseBody> request = Api.API_SERVICE.downloadFile(url);
        try {
            downloadFile(request.execute().body(), fileName);
        } catch (IOException e) {
            Timber.e(e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadFile(@NonNull ResponseBody body, final String fileName) throws IOException {
        Timber.i("downloadFile: start %s", fileName);

        int count;
        byte data[] = new byte[1024 * 4];
        File file = new File(getCacheDir() + fileName);
        if (file.exists()) file.delete();

        InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);
        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        OutputStream output = new FileOutputStream(outputFile);

        while ((count = bis.read(data)) != -1) {
            output.write(data, 0, count);
        }

        output.flush();
        output.close();
        bis.close();
        Timber.i("downloadFile: finish");
    }

}
