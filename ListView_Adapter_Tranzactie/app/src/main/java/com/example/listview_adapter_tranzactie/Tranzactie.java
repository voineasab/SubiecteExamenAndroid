package com.example.listview_adapter_tranzactie;


import android.os.Parcel;
import android.os.Parcelable;

public class Tranzactie implements Parcelable {
    int suma;
    //spinner
    String categorie;
    //radiobutton
    String tipPlata; //online/in magazin
    //timepicker
    int oraTranzatie;
    boolean reducere;

    public Tranzactie(int suma, String categorie, String tipPlata, int oraTranzatie, boolean reducere) {
        this.suma = suma;
        this.categorie = categorie;
        this.tipPlata = tipPlata;
        this.oraTranzatie = oraTranzatie;
        this.reducere = reducere;
    }

    protected Tranzactie(Parcel in) {
        suma = in.readInt();
        categorie = in.readString();
        tipPlata = in.readString();
        oraTranzatie = in.readInt();
        reducere = in.readByte() != 0;
    }

    public static final Creator<Tranzactie> CREATOR = new Creator<Tranzactie>() {
        @Override
        public Tranzactie createFromParcel(Parcel in) {
            return new Tranzactie(in);
        }

        @Override
        public Tranzactie[] newArray(int size) {
            return new Tranzactie[size];
        }
    };

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTipPlata() {
        return tipPlata;
    }

    public void setTipPlata(String tipPlata) {
        this.tipPlata = tipPlata;
    }

    public int getOraTranzatie() {
        return oraTranzatie;
    }

    public void setOraTranzatie(int oraTranzatie) {
        this.oraTranzatie = oraTranzatie;
    }

    public boolean isReducere() {
        return reducere;
    }

    public void setReducere(boolean reducere) {
        this.reducere = reducere;
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "suma=" + suma +
                ", categorie='" + categorie + '\'' +
                ", tipPlata='" + tipPlata + '\'' +
                ", oraTranzatie=" + oraTranzatie +
                ", reducere=" + reducere +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(suma);
        parcel.writeString(categorie);
        parcel.writeString(tipPlata);
        parcel.writeInt(oraTranzatie);
        parcel.writeByte((byte) (reducere ? 1 : 0));
    }
}
