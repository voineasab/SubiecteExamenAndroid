package com.example.testexamen2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FacturaAdapter extends BaseAdapter {


    public FacturaAdapter(ArrayList<Factura> facturi, Context ctx) {
        this.facturi = facturi;
        this.ctx = ctx;
    }

    ArrayList<Factura> facturi = new ArrayList<>();
    private Context ctx;

    @Override
    public int getCount() {
        return this.facturi.size();
    }

    @Override
    public Object getItem(int i) {
        return this.facturi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View v = layoutInflater.inflate(R.layout.item_gridview, viewGroup,false);
        TextView tv_loc = v.findViewById(R.id.tv_locPlata);
        TextView tv_suma = v.findViewById(R.id.tv_suma);
        TextView tv_tip = v.findViewById(R.id.tv_tipPlata);
        CheckBox checkBox = v.findViewById(R.id.checkbox);
        checkBox.setTag(Integer.valueOf(i));

        Factura f = facturi.get(i);
        tv_loc.setText(f.locPlata);
        tv_tip.setText(f.tipPlata);
        tv_suma.setText(String.valueOf(f.suma));

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx.getApplicationContext(), "On click", Toast.LENGTH_LONG).show();
                if (facturi.get(i).getSelectat()) {
                    facturi.get(i).setSelectat(false);
                }
                else
                    facturi.get(i).setSelectat(true);
            }
        });
        return v;
    }


}
