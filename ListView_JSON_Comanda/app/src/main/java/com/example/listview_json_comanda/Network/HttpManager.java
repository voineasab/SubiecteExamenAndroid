package com.example.listview_json_comanda.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class HttpManager implements Callable<String>
{

    private URL url;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    private final String urlAddress;

    public HttpManager(String urlAddress) {
        this.urlAddress = urlAddress;
    }


    @Override
    public String call() throws Exception {

        try{
            return getContentFromHttp();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnections();
        }
        return null;
    }

    private void closeConnections() throws IOException {
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
    }


    private String getContentFromHttp() throws IOException {
        url = new URL(urlAddress);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        inputStream = httpURLConnection.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder result = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine())!= null) {
            result.append(line);
        }
        return result.toString();
    }
}