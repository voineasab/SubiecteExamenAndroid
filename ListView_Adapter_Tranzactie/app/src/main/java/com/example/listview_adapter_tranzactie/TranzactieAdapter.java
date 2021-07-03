package com.example.listview_adapter_tranzactie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class TranzactieAdapter extends BaseAdapter {

    ArrayList<Tranzactie> tranzactii = new ArrayList<>();
    private Context ctx;

    public TranzactieAdapter(ArrayList<Tranzactie> tranzactii, Context ctx) {
        this.tranzactii = tranzactii;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return this.tranzactii.size();
    }

    @Override
    public Object getItem(int i) {
        return this.tranzactii.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View v = layoutInflater.inflate(R.layout.item_lista, viewGroup, false);
        TextView tv_suma = v.findViewById(R.id.tv_suma);
        TextView tv_ora = v.findViewById(R.id.tv_ora);
        TextView tv_tipPlata = v.findViewById(R.id.tv_tipPlata);
        Switch switchReducere = v.findViewById(R.id.switch_reducere);

        Tranzactie t = tranzactii.get(i);
        tv_ora.setText(String.valueOf(t.getOraTranzatie()));
        tv_suma.setText(String.valueOf(t.suma));
        tv_tipPlata.setText(t.tipPlata);
        int sumaInitiala = t.getSuma();
        switchReducere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (switchReducere.isChecked()) {
                    t.setReducere(true);
                    t.setSuma(sumaInitiala/2);

                }
                else {
                    t.setSuma(sumaInitiala);
                    t.setReducere(false);
                }
                tv_suma.setText(String.valueOf(t.getSuma()));
            }
        });
        return v;
    }
}
