package com.example.listview_adaptor_material;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import java.util.ArrayList;

public class CreareMaterial extends AppCompatActivity {

    Intent intent;
    Material m;
    EditText et_pret;
    EditText et_numeFirma;
    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton radioButtonTranzit;
    RadioButton radioButtonDepou;
    TimePicker timePicker;
    Button button;

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> listSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_material);
        intent = getIntent();
        initComponente();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent.hasExtra("deEditat")) {
                    new AlertDialog.Builder(CreareMaterial.this)
                            .setTitle("Editare obiect")
                            .setMessage("Confirmati editarea?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                    salveazaMaterial();
                                }
                            })
                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else {
                    salveazaMaterial();
                }
            }
        });

        if(intent.hasExtra("deEditat")){
            m = intent.getParcelableExtra("deEditat");
            setContentComponente();
        }
    }

    private void setContentComponente() {
        et_pret.setText(String.valueOf(m.getPret()));
        et_numeFirma.setText(m.getNumeFirma());

        int spinnerPosition = arrayAdapter.getPosition(m.tipMaterial);
        spinner.setSelection(spinnerPosition);
        if (m.getLocatie()=="Depou") {
            radioGroup.check(R.id.rb_locatie_depou);
        }
        else radioGroup.check(R.id.rb_locatie_tranzit);

        timePicker.setHour(m.oraFolosire);
    }

    private void initComponente() {
        et_pret = findViewById(R.id.et_pret);
        et_numeFirma = findViewById(R.id.et_nume_firma);
        spinner = findViewById(R.id.spinner_tip_material);
        radioGroup = findViewById(R.id.radiogroup);
        radioButtonDepou = findViewById(R.id.rb_locatie_depou);
        radioButtonTranzit = findViewById(R.id.rb_locatie_tranzit);
        timePicker = findViewById(R.id.timepicker);
        button = findViewById(R.id.btn_creare);

        ArrayList<String> listSpinner = new ArrayList<>();
        listSpinner.add("Beton");
        listSpinner.add("Ciment");
        listSpinner.add("Nisip");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinner);
        spinner.setAdapter(arrayAdapter);
    }

    private boolean validareDate() {
        if (et_pret.getText() == null || et_pret.getText().toString().trim().length() < 1) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul pret",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (et_numeFirma.getText() == null || et_numeFirma.getText().toString().trim().length() < 1) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul nume firma",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }

    private void salveazaMaterial() {
        if (validareDate()) {
            int pret = Integer.parseInt(et_pret.getText().toString());
            String numeFirma = et_numeFirma.getText().toString();
            int ora = Integer.parseInt(String.valueOf(timePicker.getHour()));
            int selectedId=radioGroup.getCheckedRadioButtonId();
            String tipMaterial = "";
            if (selectedId == radioButtonDepou.getId()) {
                tipMaterial = (String) radioButtonDepou.getText();
            }
            else {
                tipMaterial = (String) radioButtonTranzit.getText();
            }
            String locatie = spinner.getSelectedItem().toString();

            Material m = new Material(pret, tipMaterial, ora, locatie,numeFirma);
            intent.putExtra("materialAdaugat", m);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}