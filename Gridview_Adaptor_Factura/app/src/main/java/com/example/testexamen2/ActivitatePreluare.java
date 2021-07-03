package com.example.testexamen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class ActivitatePreluare extends AppCompatActivity {

    Intent intent;
    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton radioButtonNumerar;
    RadioButton radioButtonCard;
    Button button;
    TimePicker timePicker;
    EditText etId;
    EditText etSuma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate_preluare);
        initComponente();
        intent = getIntent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salveazFactura();
            }
        });
    }

    private void initComponente() {
        etId = findViewById(R.id.id_factura);
        etSuma = findViewById(R.id.et_suma);
        spinner = findViewById(R.id.spinner_tip_plata);
        timePicker = findViewById(R.id.timepicker_ora_plata);
        button = findViewById(R.id.btn_creare);
        radioGroup = findViewById(R.id.radiogroup);
        radioButtonCard = findViewById(R.id.radiobutton_card);
        radioButtonNumerar = findViewById(R.id.radiobutton_numerar);

        ArrayList<String> listSpinner = new ArrayList<>();
        listSpinner.add("Mancare");
        listSpinner.add("Utilitati");
        listSpinner.add("Hobby");
        listSpinner.add("Medicamente");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinner);
        spinner.setAdapter(arrayAdapter);


    }

    private boolean valideazaDate() {
        //validare pentru campul name
        if (etId.getText() == null || etId.getText().toString().trim().length() < 3) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul id cu > 3 cifre",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (etSuma.getText() == null || etSuma.getText().toString().trim().length() < 1) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campulsuma",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        return true;
    }

    public void salveazFactura() {
        if (valideazaDate()) {
            int id = Integer.parseInt(String.valueOf(etId.getText()));
            int suma = Integer.parseInt(etSuma.getText().toString());
            int selectedId=radioGroup.getCheckedRadioButtonId();
            String tipPlata = "";
            if (selectedId == radioButtonNumerar.getId()) {
                tipPlata = (String) radioButtonNumerar.getText();
            }
            else {
                tipPlata = (String) radioButtonCard.getText();
            }
            Toast.makeText(this, String.valueOf(radioGroup.getCheckedRadioButtonId()), Toast.LENGTH_LONG);

            int ora = timePicker.getHour();

            String locPlata = spinner.getSelectedItem().toString();

            Factura factura = new Factura(id, tipPlata, ora, locPlata, suma);
            intent.putExtra("facturaAdaugata", factura);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}