package com.example.listview_adapter_tranzactie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class CreareTranzactie extends AppCompatActivity {
    Intent intent;
    EditText et_suma;
    TimePicker timePicker;
    RadioButton rb_online;
    RadioButton rb_magazin;
    RadioGroup radioGroup;
    Spinner spinner;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_tranzactie);
        intent = getIntent();
        initComponente();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salveazaTranzactie();
            }
        });

    }

    private void initComponente(){
        et_suma= findViewById(R.id.et_suma);
        timePicker = findViewById(R.id.timepicker);
        rb_online = findViewById(R.id.radiobutton_online);
        rb_magazin = findViewById(R.id.radiobutton_magazin);
        radioGroup = findViewById(R.id.radiogroup);
        spinner = findViewById(R.id.spinner_categorie);
        button = findViewById(R.id.btn_creare);

        ArrayList<String> listSpinner = new ArrayList<>();
        listSpinner.add("Mancare");
        listSpinner.add("Utilitati");
        listSpinner.add("Hobby");
        listSpinner.add("Medicamente");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinner);
        spinner.setAdapter(arrayAdapter);

    }

    private boolean validareDate() {
        if (et_suma.getText() == null || et_suma.getText().toString().trim().length() < 1) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul suma",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }

    private void salveazaTranzactie() {
        if (validareDate()) {
            int suma = Integer.parseInt(et_suma.getText().toString());
            int ora = Integer.parseInt(String.valueOf(timePicker.getHour()));
            int selectedId=radioGroup.getCheckedRadioButtonId();
            String tipPlata = "";
            if (selectedId == rb_online.getId()) {
                tipPlata = (String) rb_online.getText();
            }
            else {
                tipPlata = (String) rb_magazin.getText();
            }
            String categorie = spinner.getSelectedItem().toString();

            Tranzactie t = new Tranzactie(suma, categorie, tipPlata, ora, false);
            intent.putExtra("tranzactieAdaugata", t);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}