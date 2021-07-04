package com.example.listview_adaptor_material;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int COD_ADAUGARE_MATERIALE = 111;
    int COD_EDITARE_MATERIAL = 222;
    public int pozitieSelectata;
    ListView listView;
    Button button;
    Button filtrare;
    MaterialeAdapter materialeAdapter;
    ArrayList<Material> materiale = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponente();
        materialeAdapter = new MaterialeAdapter(materiale, this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreareMaterial.class);
                startActivityForResult(intent, COD_ADAUGARE_MATERIALE);
            }
        });

        filtrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Material> materiale2 = new ArrayList<>();
                materiale2 = materiale;
                for (Material material : materiale2) {
                    if (material.getPret() < 500) {
                        materiale2.remove(material);
                    }
                }
                materiale = materiale2;
                materialeAdapter.notifyDataSetChanged();
            }
        });

    }

    private void initComponente() {
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.btn_main_creare);
        filtrare = findViewById(R.id.btn_filtrare);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_ADAUGARE_MATERIALE) {
            if (resultCode == RESULT_OK) {
                Material m = data.getParcelableExtra("materialAdaugat");
                materiale.add(m);
                adaugaMaterialInLista();
            }
        }

        if(requestCode == COD_EDITARE_MATERIAL) {

            if (resultCode == RESULT_OK) {
                Log.e("test", "test");
                Material m = data.getParcelableExtra("materialAdaugat");
                pozitieSelectata = data.getIntExtra("pozitieSelectata", 0);
                Log.e("pozitie", String.valueOf(pozitieSelectata));
                Material material = materiale.get(pozitieSelectata);
                material.setPret(m.getPret());
                material.setNumeFirma(m.getNumeFirma());
                material.setLocatie(m.getLocatie());
                material.setOraFolosire(m.getOraFolosire());
                material.setTipMaterial(m.getTipMaterial());
                Log.e("material", material.toString());
                adaugaMaterialInLista();

            }
        }
    }
    private void adaugaMaterialInLista() {
        listView.setAdapter(materialeAdapter);
    }




}