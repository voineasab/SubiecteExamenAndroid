package com.example.testexamen2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button;
    GridView gridView;
    FacturaAdapter facturaAdapter;
    //Intent intent;
    int COD_ADAUGARE_FACTURA = 111;

    String fileName = "fileExamen.txt";
    String filePath = "fisierPath";
    File fisierExtern;


    ArrayList<Factura> facturi = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponente();

        facturaAdapter = new FacturaAdapter(facturi, this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivitatePreluare.class);
                startActivityForResult(intent, COD_ADAUGARE_FACTURA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == COD_ADAUGARE_FACTURA){
            if(resultCode == RESULT_OK) {
                Factura factura = data.getParcelableExtra("facturaAdaugata");
                facturi.add(factura);
                Toast.makeText(getApplicationContext(),factura.toString(),Toast.LENGTH_LONG).show();
                adaugaFacturaInGridview();
            }
        }
        /*
        if(requestCode == COD_LISTA){
            if(resultCode == RESULT_OK) {
                ArrayList<Articol>  articolePrimite = data.getParcelableArrayListExtra("articoleRezultat");
                articole = articolePrimite;
            }
        }

         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_actualizare:
                for (Factura factura : facturi) {
                    factura.id = factura.id*10;
                }
                adaugaFacturaInGridview();
                return true;
            case R.id.menu_salvare:
                try {
                    FileOutputStream fos = null;
                    fos = openFileOutput(fileName, MODE_PRIVATE);
                    fos.write(facturi.toString().getBytes());
                    Toast.makeText(getApplicationContext(),"Fisier.txt salvat extern in " + getFilesDir() + "/" + fileName, Toast.LENGTH_LONG).show();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
                return true;
            case R.id.menu_stergere:
                for (Factura factura : facturi) {
                    if (factura.getSelectat() == true) {
                        facturi.remove(facturi.indexOf(factura));
                        //gridView.remove(facturi.indexOf(factura));
                        facturaAdapter.notifyDataSetChanged();
                    }
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initComponente(){
        button = findViewById(R.id.btn_adauga_factura);
        gridView = findViewById(R.id.gridView);

    }

    private void adaugaFacturaInGridview() {
        gridView.setAdapter(facturaAdapter);
    }
}