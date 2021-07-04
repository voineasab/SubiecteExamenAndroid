package com.example.listview_adaptor_material;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MaterialeAdapter extends BaseAdapter {

    int COD_EDITARE_MATERIAL = 222;
    int POZITIE_SELECTATA;
    ArrayList<Material> materiale = new ArrayList<>();
    private Context ctx;

    public MaterialeAdapter(ArrayList<Material> materiale, Context ctx) {
        this.materiale = materiale;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return this.materiale.size();
    }

    @Override
    public Object getItem(int i) {
        return this.materiale.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View v =layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        TextView tv_pret = v.findViewById(R.id.tv_pret);
        TextView tv_numeFirma = v.findViewById(R.id.tv_numeFirma);
        TextView tv_locatie = v.findViewById(R.id.tv_locatie);

        Material m = materiale.get(i);
        tv_pret.setText(String.valueOf(m.getPret()));
        tv_numeFirma.setText(m.getNumeFirma());
        tv_locatie.setText(m.getLocatie());

        Button clasificare = v.findViewById(R.id.btn_clasificare);
        clasificare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m.getPret() < 100) {
                    v.setBackgroundColor(Color.RED);
                }
                else if (m.getPret() > 100 && m.getPret() <1000) {
                    v.setBackgroundColor(Color.YELLOW);
                }
                else {
                    v.setBackgroundColor(Color.GREEN);
                }
            }
        });

        Button editare = v.findViewById(R.id.btn_editare);
        editare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                POZITIE_SELECTATA = materiale.indexOf(materiale.get(i));
                Intent intent = new Intent(ctx, CreareMaterial.class);
                intent.putExtra("deEditat", m);
                intent.putExtra("pozitieSelectata",POZITIE_SELECTATA);
                ((Activity) ctx).startActivityForResult(intent, COD_EDITARE_MATERIAL);
            }
        });

        return v;
    }
}
