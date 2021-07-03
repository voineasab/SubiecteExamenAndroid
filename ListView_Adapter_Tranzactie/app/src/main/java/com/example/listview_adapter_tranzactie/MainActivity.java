package com.example.listview_adapter_tranzactie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Tranzactie> list1 = new ArrayList<>();
    ArrayList<Tranzactie> list2= new ArrayList<>();

    TranzactieAdapter tranzactieAdapter2;
    TranzactieAdapter tranzactieAdapter1;
    ListView listView1;
    ListView listView2;
    Button button;
    int COD_ADAUGARE_TRANZACTIE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponente();
        tranzactieAdapter1 = new TranzactieAdapter(list1, this);
        tranzactieAdapter2 = new TranzactieAdapter(list2, this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreareTranzactie.class);
                startActivityForResult(intent, 111);
            }
        });


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list1.get(i).isReducere() == true) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Stergere obiect")
                            .setMessage("Confirmati stergerea?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                    list1.remove(i);
                                    Log.e("Lista:" , list1.toString());
                                    tranzactieAdapter1.notifyDataSetChanged();
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list2.add(list1.get(i));
                list1.remove(list1.get(i));
                adaugaTranzactieInListview();
                tranzactieAdapter1.notifyDataSetChanged();
                return true;
            }
        });

        listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list1.add(list2.get(i));
                list2.remove(list2.get(i));
                adaugaTranzactieInListview();
                tranzactieAdapter1.notifyDataSetChanged();
                tranzactieAdapter2.notifyDataSetChanged();
                return true;
            }
        });

    }

    private void initComponente() {
        listView1 = findViewById(R.id.listview1);
        listView2 = findViewById(R.id.listview2);
        button = findViewById(R.id.main_creare);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Tranzactie t = data.getParcelableExtra("tranzactieAdaugata");
            list1.add(t);
            Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            adaugaTranzactieInListview();
        }
    }

    private void adaugaTranzactieInListview() {
        listView1.setAdapter(tranzactieAdapter1);
        listView2.setAdapter(tranzactieAdapter2);
    }


}