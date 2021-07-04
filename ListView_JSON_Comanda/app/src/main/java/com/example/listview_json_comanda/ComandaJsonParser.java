package com.example.listview_json_comanda;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComandaJsonParser {
    public static List<Comanda> fromJson(String json) {
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            try {
                JSONArray array = new JSONArray(json);
                List<Comanda> listaCarduri = new ArrayList<>();
                for (int i=0;i<array.length();i++) {
                    JSONObject object = array.getJSONObject(i);
                    String nume = object.getString("nume");
                    int pret = Integer.parseInt(object.getString("pret"));
                    String tipPlata = object.getString("tipPlata");
                    String tipMancare = object.getString("tipMancare");
                    Date data = null;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    try {
                        data = format.parse(String.valueOf(data));
                        System.out.println(data);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    listaCarduri.add(new Comanda(data, nume, tipPlata, tipMancare, pret));
                }
                return listaCarduri;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }

}
