package com.example.listview_json_comanda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreareComanda extends AppCompatActivity {

    ArrayAdapter arrayAdapter;
    Intent intent;
    Comanda c;
    Button creare;
    EditText et_nume;
    EditText et_pret;
    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton card;
    RadioButton numerar;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_comanda);
        initComponente();
        intent=getIntent();
        creare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salveazaDate();
            }
        });

        if(intent.hasExtra("deEditat")){
            c = intent.getParcelableExtra("deEditat");
            setContentComponente();
        }
    }

    private void setContentComponente() {
        et_nume.setText(c.getNume());
        et_pret.setText(String.valueOf(c.getPret()));
        int spinnerPosition = arrayAdapter.getPosition(c.tipMancare);
        spinner.setSelection(spinnerPosition);
        if (c.getData() != null) {
            datePicker.updateDate(c.getData().getYear(), c.getData().getMonth(), c.getData().getDay());
        }

        if (c.getTipPlata() == "Card") {
            radioGroup.check(R.id.rb_card);
        }
        else {
            radioGroup.check(R.id.rb_numerar);
        }
    }

    private void initComponente() {
        creare = findViewById(R.id.btn_creare);
        et_nume = findViewById(R.id.et_nume);
        et_pret = findViewById(R.id.et_pret);
        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radiogroup);
        card = findViewById(R.id.rb_card);
        numerar = findViewById(R.id.rb_numerar);
        datePicker = findViewById(R.id.datepicker);

        ArrayList<String> listSpinner = new ArrayList<>();
        listSpinner.add("Supa");
        listSpinner.add("Felul 2");
        listSpinner.add("Desert");
        listSpinner.add("Bauturi");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinner);
        spinner.setAdapter(arrayAdapter);
    }
    private boolean valideazaDate() {
        //validare pentru campul name
        if (et_nume.getText() == null || et_nume.getText().toString().trim().length() < 3) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul nume",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (et_pret.getText() == null || et_pret.getText().toString().trim().length() < 1) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul pret",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        return true;
    }

    private void salveazaDate() {
        if (valideazaDate()) {

            Calendar c = new GregorianCalendar();
            c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            Date data = c.getTime();
            Log.e("Time", data.toString());

            int pret = Integer.parseInt(et_pret.getText().toString());
            String nume = et_nume.getText().toString();
            int selectedId = radioGroup.getCheckedRadioButtonId();
            String tipPlata = "";
            if (selectedId == R.id.rb_card) {
                tipPlata = "Card";
            }
            else {
                tipPlata = "Numerar";
            }

            String tipMancare = (String) spinner.getSelectedItem();

            Comanda comanda = new Comanda(data, nume, tipPlata, tipMancare, pret);
            intent.putExtra("comandaAdaugata", comanda);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}