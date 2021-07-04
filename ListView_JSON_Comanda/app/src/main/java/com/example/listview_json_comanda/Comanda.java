package com.example.listview_json_comanda;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Comanda implements Parcelable {

    //restaurant -Spinner, Radiobutton, datepicker
    Date data;
    String nume;
    String tipPlata; //radiobutton
    String tipMancare; //spinner
    int pret;

    public Comanda(Date data, String nume, String tipPlata, String tipMancare, int pret) {
        this.data = data;
        this.nume = nume;
        this.tipPlata = tipPlata;
        this.tipMancare = tipMancare;
        this.pret = pret;
    }


    protected Comanda(Parcel in) {
        nume = in.readString();
        tipPlata = in.readString();
        tipMancare = in.readString();
        pret = in.readInt();
        data = new Date(in.readLong());

    }

    public static final Creator<Comanda> CREATOR = new Creator<Comanda>() {
        @Override
        public Comanda createFromParcel(Parcel in) {
            return new Comanda(in);
        }

        @Override
        public Comanda[] newArray(int size) {
            return new Comanda[size];
        }
    };

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTipPlata() {
        return tipPlata;
    }

    public void setTipPlata(String tipPlata) {
        this.tipPlata = tipPlata;
    }

    public String getTipMancare() {
        return tipMancare;
    }

    public void setTipMancare(String tipMancare) {
        this.tipMancare = tipMancare;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "data=" + data +
                ", nume='" + nume + '\'' +
                ", tipPlata='" + tipPlata + '\'' +
                ", tipMancare='" + tipMancare + '\'' +
                ", pret=" + pret +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nume);
        parcel.writeString(tipPlata);
        parcel.writeString(tipMancare);
        parcel.writeInt(pret);
        if (data!=null) {
            parcel.writeLong(data.getTime());
        }
    }
}
