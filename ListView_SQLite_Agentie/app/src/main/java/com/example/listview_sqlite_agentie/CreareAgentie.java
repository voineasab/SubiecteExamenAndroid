package com.example.listview_sqlite_agentie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.listview_sqlite_agentie.db.AppDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreareAgentie extends AppCompatActivity {

    Intent intent;
    EditText et_nume;
    EditText et_adresa;
    SeekBar seekBar;
    DatePicker datePicker;
    RadioGroup radioGroup;
    RadioButton rb_startup;
    RadioButton rb_afacereMare;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_agentie);
        intent = getIntent();
        initComponente();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salveazaDate();
            }
        });
    }

    private void initComponente() {
        et_nume = findViewById(R.id.et_nume);
        et_adresa = findViewById(R.id.et_adresa);
        seekBar = findViewById(R.id.seekbar);
        datePicker = findViewById(R.id.datepicker);
        radioGroup = findViewById(R.id.radiogroup);
        rb_startup = findViewById(R.id.radiobutton_start_up);
        rb_afacereMare = findViewById(R.id.radiobutton_firma_mare);
        button = findViewById(R.id.btn_creare);
    }

    private boolean validareDate() {
        //validare pentru campul name
        if (et_nume.getText() == null || et_nume.getText().toString().trim().length() < 2) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul nume.",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (et_adresa.getText() == null || et_adresa.getText().toString().trim().length() < 1) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul adresa.",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        return true;
    }

    private void salveazaDate() {
        if (validareDate()) {
            AppDatabase db  = AppDatabase.getInstance(this.getApplicationContext());

            Calendar c = new GregorianCalendar();
            c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            Date data = c.getTime();

            int nrClienti = seekBar.getProgress();
            String nume = et_nume.getText().toString();
            String adresa = et_adresa.getText().toString();

            int selectedId = radioGroup.getCheckedRadioButtonId();
            String dimensiune = "";
            if (selectedId == rb_afacereMare.getId()) {
                dimensiune = rb_afacereMare.getText().toString();
            }
            else {
                dimensiune = rb_startup.getText().toString();
            }

            Agentie ag = new Agentie(nume, adresa, data, dimensiune, nrClienti);
            db.agentieDao().insertAgentie(ag);
            intent.putExtra("agentieAdaugata", ag);
            setResult(RESULT_OK,intent);
            finish();
        }
    }

}