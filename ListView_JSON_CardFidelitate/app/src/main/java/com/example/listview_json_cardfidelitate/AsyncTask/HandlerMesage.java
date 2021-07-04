package com.example.listview_json_cardfidelitate.AsyncTask;

public class HandlerMesage<R> implements Runnable{
    private final R result;
    private final Callback<R> mainThreadOperation;

    public HandlerMesage(R result, Callback<R> mainThreadOperation) {
        this.result = result;
        this.mainThreadOperation = mainThreadOperation;
    }


    @Override
    public void run() {
        mainThreadOperation.runResultOnUiThread(result);
    }
}
