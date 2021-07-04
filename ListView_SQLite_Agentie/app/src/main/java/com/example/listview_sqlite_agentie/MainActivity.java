package com.example.listview_sqlite_agentie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.listview_sqlite_agentie.db.AppDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int COD_ADAUGARE =111;
    ListView listView;
    Button  button;
    ArrayList<Agentie> listAgentii = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadListaAgentii();
        initComponente();
        adaugaInListview();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreareAgentie.class);
                startActivityForResult(intent, COD_ADAUGARE);
            }
        });
    }

    private void initComponente() {
        listView=findViewById(R.id.listView);
        button = findViewById(R.id.btn_main_creare);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Agentie agentie = data.getParcelableExtra("agentieAdaugata");
        listAgentii.add(agentie);
        adaugaInListview();
        loadListaAgentii();

    }

    private void adaugaInListview() {
        ArrayAdapter<Agentie> agentieArrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listAgentii);
        listView.setAdapter(agentieArrayAdapter);
    }

    private void loadListaAgentii() {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        listAgentii.addAll(db.agentieDao().getAllAgentii());
    }
}