package com.example.listview_json_cardfidelitate.AsyncTask;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncTaskRunner {
    private final Executor executor = Executors.newCachedThreadPool();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public <R> void executeAsync(Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
         try {
             executor.execute(new RunnableTask<>(handler, asyncOperation, mainThreadOperation));
         }

         catch (Exception ex) {
            Log.e("AsyncTaskRunner", "Failed call execute async");
         }

    }}

