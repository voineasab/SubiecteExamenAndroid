package com.example.listview_json_cardfidelitate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreareCard extends AppCompatActivity {

    Intent intent;
    CardFidelitate card;
    EditText et_nume;
    EditText et_tara;
    SeekBar seekBar;
    DatePicker datePicker;
    RadioGroup radioGroup;
    RadioButton radioButtonReducere;
    RadioButton radioButtonProdus;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_card);
        initComponente();

        intent=getIntent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvareCard();
            }
        });

        if(intent.hasExtra("deEditat")){
            card = intent.getParcelableExtra("deEditat");
            setContentComponente();
        }
    }

    private void initComponente() {
        et_nume = findViewById(R.id.et_nume);
        et_tara = findViewById(R.id.et_tara);
        seekBar = findViewById(R.id.seekBar);
        datePicker = findViewById(R.id.datepicker);
        radioGroup = findViewById(R.id.radiogroup);
        radioButtonReducere = findViewById(R.id.rb_reducere);
        radioButtonProdus = findViewById(R.id.rb_pr_gratuit);
        button = findViewById(R.id.btn_creare);
        seekBar.setMax(100);
    }
    private void setContentComponente() {
        et_nume.setText(card.getNume());
        et_tara.setText(card.getTara());
        seekBar.setProgress(card.getNrPuncte());
        if (card.getDataExpirare() != null) {
            datePicker.updateDate(card.dataExpirare.getYear(), card.getDataExpirare().getMonth(), card.getDataExpirare().getDay());
        }
        if (card.getPremiu()=="Produs gratuit") {
            radioGroup.check(R.id.rb_pr_gratuit);
        }
        else {
            radioGroup.check(R.id.rb_reducere);
        }
    }
    private boolean validareDate() {
        if (et_nume.getText() == null || et_nume.getText().toString().trim().length() < 1) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul nume corect",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (et_tara.getText() == null || et_tara.getText().toString().trim().length() < 1) {

            Toast.makeText(getApplicationContext(),
                    "Trebuie completat campul tara corect",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }

    private void salvareCard() {
        if (validareDate()) {
            String nume = et_nume.getText().toString();
            String tara = et_tara.getText().toString();

            int selectedId=radioGroup.getCheckedRadioButtonId();
            String premiu = "";
            if (selectedId == radioButtonProdus.getId()) {
                premiu = (String) radioButtonProdus.getText();
            }
            else {
                premiu = (String) radioButtonReducere.getText();
            }
            int nrPuncte = seekBar.getProgress();

            Calendar c = new GregorianCalendar();
            c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            Date dataExpirare = c.getTime();
            Log.e("Time", dataExpirare.toString());

            CardFidelitate card = new CardFidelitate(nume, tara, nrPuncte, premiu, dataExpirare);
            intent.putExtra("cardAdaugat", card);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}