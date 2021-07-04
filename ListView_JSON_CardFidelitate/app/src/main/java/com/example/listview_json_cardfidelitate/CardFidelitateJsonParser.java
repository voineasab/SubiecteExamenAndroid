package com.example.listview_json_cardfidelitate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardFidelitateJsonParser {
    public static List<CardFidelitate> fromJson(String json) {
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            try {
                JSONArray array = new JSONArray(json);
                List<CardFidelitate> listaCarduri = new ArrayList<>();
                for (int i=0;i<array.length();i++) {
                    JSONObject object = array.getJSONObject(i);
                    String nume = object.getString("nume");
                    String tara = object.getString("tara");
                    int nrPuncte = Integer.parseInt(object.getString("nrPuncte"));
                    String premiu = object.getString("premiu");
                    String date = object.getString("dataExpirare");
                    Date dataExpirare = null;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    try {
                        dataExpirare = format.parse(date);
                        System.out.println(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    listaCarduri.add(new CardFidelitate(nume, tara, nrPuncte, premiu, dataExpirare));
                }
                return listaCarduri;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }
}
