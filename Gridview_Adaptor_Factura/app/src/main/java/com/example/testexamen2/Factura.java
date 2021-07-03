package com.example.testexamen2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Factura implements Parcelable {

    //spinner/ radiobutton/ timepicker
    int id;
    String tipPlata; //radiobutton
    int oraPlata; //timepicker
    String locPlata; //unde s-a facut plata
    int suma;
    boolean selectat;

    public Factura(int id, String tipPlata, int oraPlata, String locPlata, int suma) {
        this.id = id;
        this.tipPlata = tipPlata;
        this.oraPlata = oraPlata;
        this.locPlata = locPlata;
        this.suma = suma;
    }

    protected Factura(Parcel in) {
        id = in.readInt();
        tipPlata = in.readString();
        oraPlata = in.readInt();
        locPlata = in.readString();
        suma = in.readInt();
    }

    public static final Creator<Factura> CREATOR = new Creator<Factura>() {
        @Override
        public Factura createFromParcel(Parcel in) {
            return new Factura(in);
        }

        @Override
        public Factura[] newArray(int size) {
            return new Factura[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipPlata() {
        return tipPlata;
    }

    public void setTipPlata(String tipPlata) {
        this.tipPlata = tipPlata;
    }

    public int getOraPlata() {
        return oraPlata;
    }

    public void setOraPlata(int oraPlata) {
        this.oraPlata = oraPlata;
    }

    public String getLocPlata() {
        return locPlata;
    }

    public void setLocPlata(String locPlata) {
        this.locPlata = locPlata;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public boolean isSelectat() {
        return selectat;
    }

    public void setSelectat(boolean selectat) {
        this.selectat = selectat;
    }

    public boolean getSelectat() {
        return this.selectat;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", tipPlata='" + tipPlata + '\'' +
                ", oraPlata=" + oraPlata +
                ", locPlata='" + locPlata + '\'' +
                ", suma='" + suma + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(tipPlata);
        parcel.writeInt(oraPlata);
        parcel.writeString(locPlata);
        parcel.writeInt(suma);
    }
}
