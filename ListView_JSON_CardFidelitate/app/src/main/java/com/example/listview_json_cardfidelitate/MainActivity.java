package com.example.listview_json_cardfidelitate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listview_json_cardfidelitate.AsyncTask.AsyncTaskRunner;
import com.example.listview_json_cardfidelitate.AsyncTask.Callback;
import com.example.listview_json_cardfidelitate.network.HttpManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "sharedPref";
    private static final String SHARED_PREF_TEXT="List Text";
    private String text;
    private static final String URL_CARD = "https://pastebin.com/raw/utaVEQm2";
    ArrayList<CardFidelitate> listCard = new ArrayList<>();
    int COD_ADAUGARE_CARD = 111;
    int COD_EDITARE_CARD = 222;
    ListView listView;
    Button creare;
    int pozitieSelectata;
    Button filtrare;
    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponente();
        getDataFromNetwork();
        creare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreareCard.class);
                startActivityForResult(intent, COD_ADAUGARE_CARD);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, CreareCard.class);
                pozitieSelectata = listCard.indexOf(listCard.get(i));
                intent.putExtra("deEditat", listCard.get(i));
                startActivityForResult(intent, COD_EDITARE_CARD);
            }
        });

        filtrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<CardFidelitate> card2 = new ArrayList<>();
                for (int i=0;i<listCard.size();i++) {
                    if (listCard.get(i).getNrPuncte() < 50) {
                        listCard.remove(listCard.get(i));
                    }
                }
                adaugaInListview();
            }
        });

        loadDataFromSharedPrefs();
    }


    private void initComponente() {
        listView=findViewById(R.id.listView);
        creare = findViewById(R.id.btn_main_creare);
        filtrare = findViewById(R.id.btn_main_filtrare);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_ADAUGARE_CARD) {
            CardFidelitate cardFidelitate = data.getParcelableExtra("cardAdaugat");
            listCard.add(cardFidelitate);
            adaugaInListview();

        }

        if (requestCode == COD_EDITARE_CARD) {
            CardFidelitate cardFidelitate = data.getParcelableExtra("cardAdaugat");
            CardFidelitate c = listCard.get(pozitieSelectata);
            c.setTara(cardFidelitate.getTara());
            c.setDataExpirare(cardFidelitate.getDataExpirare());
            c.setNrPuncte(cardFidelitate.getNrPuncte());
            c.setNume(cardFidelitate.getNume());
            c.setPremiu(cardFidelitate.getPremiu());
            adaugaInListview();
        }

        SaveToSharedPrefs();
    }

    private void adaugaInListview() {
        ArrayAdapter<CardFidelitate> cardFidelitateArrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listCard);
        listView.setAdapter(cardFidelitateArrayAdapter);
        SaveToSharedPrefs();
    }
    private void getDataFromNetwork() {
        Callable<String> asyncOperation = new HttpManager(URL_CARD);
        Callback<String> mainThreadOperation = getMainThreadOperationForCards();
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }


    private Callback<String> getMainThreadOperationForCards() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                listCard.addAll(CardFidelitateJsonParser.fromJson(result));
                adaugaInListview();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case(R.id.ordonare):
                Collections.sort(listCard);
                adaugaInListview();
                return true;
            default:
                return true;
        }
    }

    private void SaveToSharedPrefs() {
        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREF_TEXT, listCard.toString());
        editor.commit();
    }

    public void loadDataFromSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        text=sharedPreferences.getString(SHARED_PREF_TEXT, null);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

    }

}