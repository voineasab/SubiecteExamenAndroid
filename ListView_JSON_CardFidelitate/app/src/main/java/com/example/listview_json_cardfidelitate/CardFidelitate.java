package com.example.listview_json_cardfidelitate;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class CardFidelitate implements Parcelable, Comparable {
    //seekbar, radiobutton, datepicker
    String nume;
    String tara;
    int nrPuncte;
    String premiu;
    Date dataExpirare;

    public CardFidelitate(String nume, String tara, int nrPuncte, String premiu, Date dataExpirare) {
        this.nume = nume;
        this.tara = tara;
        this.nrPuncte = nrPuncte;
        this.premiu = premiu;
        this.dataExpirare = dataExpirare;
    }

    protected CardFidelitate(Parcel in) {
        nume = in.readString();
        tara = in.readString();
        nrPuncte = in.readInt();
        premiu = in.readString();
        dataExpirare = new Date(in.readLong());
    }

    public static final Creator<CardFidelitate> CREATOR = new Creator<CardFidelitate>() {
        @Override
        public CardFidelitate createFromParcel(Parcel in) {
            return new CardFidelitate(in);
        }

        @Override
        public CardFidelitate[] newArray(int size) {
            return new CardFidelitate[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public int getNrPuncte() {
        return nrPuncte;
    }

    public void setNrPuncte(int nrPuncte) {
        this.nrPuncte = nrPuncte;
    }

    public String getPremiu() {
        return premiu;
    }

    public void setPremiu(String premiu) {
        this.premiu = premiu;
    }

    public Date getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Date dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    @Override
    public String toString() {
        return "CardFidelitate{" +
                "nume='" + nume + '\'' +
                ", tara='" + tara + '\'' +
                ", nrPuncte=" + nrPuncte +
                ", premiu='" + premiu + '\'' +
                ", dataExpirare=" + dataExpirare +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nume);
        parcel.writeString(tara);
        parcel.writeInt(nrPuncte);
        parcel.writeString(premiu);
        if (dataExpirare!=null) {
            parcel.writeLong(dataExpirare.getTime());
        }
    }

    @Override
    public int compareTo(Object o) {
        int comparenrPuncte =((CardFidelitate)o).getNrPuncte();
        return this.nrPuncte-comparenrPuncte;
    }
}
