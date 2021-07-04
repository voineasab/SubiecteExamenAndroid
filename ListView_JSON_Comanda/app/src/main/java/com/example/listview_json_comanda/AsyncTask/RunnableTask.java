package com.example.listview_json_comanda.AsyncTask;

import android.os.Handler;
import android.util.Log;

import com.example.listview_json_comanda.AsyncTask.Callback;
import com.example.listview_json_comanda.AsyncTask.HandlerMessage;

import java.util.concurrent.Callable;

public class RunnableTask<R> implements Runnable {

    private final Handler handler;
    private final Callable<R> asyncOperation;
    private final Callback<R> mainThreadOperation;

    public RunnableTask(Handler handler, Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
        this.handler = handler;
        this.asyncOperation = asyncOperation;
        this.mainThreadOperation = mainThreadOperation;
    }

    @Override
    public void run() {
        try {
            final R result = asyncOperation.call();
            handler.post(new HandlerMessage<>(result, mainThreadOperation));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Runnable task", "Failed call runnable task");
        }
    }
}
