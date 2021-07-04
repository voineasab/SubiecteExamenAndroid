package com.example.listview_adaptor_material;

import android.os.Parcel;
import android.os.Parcelable;

public class Material implements Parcelable {
    //spinner
    //radiobutton
    //timepicker
    int pret;
    String tipMaterial; //spinner
    int oraFolosire; //timepicker
    String locatie; //radiobutton : in tranzit / valabil
    String numeFirma;

    public Material(int pret, String tipMaterial, int oraFolosire, String locatie, String numeFirma) {
        this.pret = pret;
        this.tipMaterial = tipMaterial;
        this.oraFolosire = oraFolosire;
        this.locatie = locatie;
        this.numeFirma = numeFirma;
    }

    protected Material(Parcel in) {
        pret = in.readInt();
        tipMaterial = in.readString();
        oraFolosire = in.readInt();
        locatie = in.readString();
        numeFirma = in.readString();
    }

    public static final Creator<Material> CREATOR = new Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel in) {
            return new Material(in);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getTipMaterial() {
        return tipMaterial;
    }

    public void setTipMaterial(String tipMaterial) {
        this.tipMaterial = tipMaterial;
    }

    public int getOraFolosire() {
        return oraFolosire;
    }

    public void setOraFolosire(int oraFolosire) {
        this.oraFolosire = oraFolosire;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getNumeFirma() {
        return numeFirma;
    }

    public void setNumeFirma(String numeFirma) {
        this.numeFirma = numeFirma;
    }

    @Override
    public String toString() {
        return "Material{" +
                "pret=" + pret +
                ", tipMaterial='" + tipMaterial + '\'' +
                ", oraFolosire=" + oraFolosire +
                ", locatie='" + locatie + '\'' +
                ", numeFirma='" + numeFirma + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(pret);
        parcel.writeString(tipMaterial);
        parcel.writeInt(oraFolosire);
        parcel.writeString(locatie);
        parcel.writeString(numeFirma);
    }
}
