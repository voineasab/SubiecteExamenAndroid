package com.example.listview_json_comanda;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview_json_comanda.AsyncTask.AsyncTaskRunner;
import com.example.listview_json_comanda.AsyncTask.Callback;
import com.example.listview_json_comanda.Network.HttpManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    int pozitieSelectata;
    String fileName = "examen.json";
    ArrayList<Comanda> listaComenzi = new ArrayList<>();
    int COD_ADAUGARE = 111;
    int COD_EDITARE = 222;
    ListView listView;
    Button button;
    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
    private static final String URL_COMANDA = "https://pastebin.com/raw/ERvXB8w9";
    String json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponente();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreareComanda.class);
                startActivityForResult(intent, COD_ADAUGARE);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, CreareComanda.class);
                pozitieSelectata = listaComenzi.indexOf(listaComenzi.get(i));
                intent.putExtra("deEditat", listaComenzi.get(i));
                startActivityForResult(intent, COD_EDITARE);
            }
        });
    }

    private void initComponente() {
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.btn_main_creare);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_ADAUGARE) {
            if (resultCode == RESULT_OK) {
                Comanda comandaPrimita = data.getParcelableExtra("comandaAdaugata");
                listaComenzi.add(comandaPrimita);
                adaugaInListView();
            }
        }
        if (requestCode == COD_EDITARE) {
            if (resultCode ==RESULT_OK) {
                Comanda comanda = data.getParcelableExtra("comandaAdaugata");
                Comanda c = listaComenzi.get(pozitieSelectata);
                c.setData(comanda.getData());
                c.setNume(comanda.getNume());
                c.setPret(comanda.getPret());
                c.setTipPlata(comanda.getTipPlata());
                c.setTipMancare(comanda.tipMancare);
                adaugaInListView();

            }
        }
    }

    private void adaugaInListView() {
        ArrayAdapter<Comanda> comandaArrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listaComenzi);
        listView.setAdapter(comandaArrayAdapter);
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
            case(R.id.menu_save):
                saveToFile();
                return true;
            case (R.id.menu_download):
                getDataFromNetwork();
                return true;
            default:
                return true;
        }
    }

    private void getDataFromNetwork() {
        Callable<String> asyncOperation = new HttpManager(URL_COMANDA);
        Callback<String> mainThreadOperation = getMainThreadOperationForComenzi();
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> getMainThreadOperationForComenzi() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                json = result;
                listaComenzi.addAll(ComandaJsonParser.fromJson(result));
                adaugaInListView();
            }
        };
    }

    private void saveToFile() {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(json.getBytes());
            Toast.makeText(getApplicationContext(),"examen.json salvat extern in " + getFilesDir() + "/" + fileName, Toast.LENGTH_LONG).show();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}