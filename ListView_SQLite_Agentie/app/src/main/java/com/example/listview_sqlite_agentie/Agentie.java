package com.example.listview_sqlite_agentie;



import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Agentie implements Parcelable {
    @ColumnInfo(name="nume")
    String nume;
    @ColumnInfo(name="adresa")
    String adresa;
    @ColumnInfo(name="dataIntalnire")
    Date dataIntalnire; //datepicker
    //radiobutton
    @ColumnInfo(name="marime")
    String marime;
    @PrimaryKey
    int nrClienti; //seekbar

    public Agentie(String nume, String adresa, Date dataIntalnire, String marime, int nrClienti) {
        this.nume = nume;
        this.adresa = adresa;
        this.dataIntalnire = dataIntalnire;
        this.marime = marime;
        this.nrClienti = nrClienti;
    }

    protected Agentie(Parcel in) {
        nume = in.readString();
        adresa = in.readString();
        marime = in.readString();
        nrClienti = in.readInt();
        dataIntalnire = new Date(in.readLong());
    }

    public static final Creator<Agentie> CREATOR = new Creator<Agentie>() {
        @Override
        public Agentie createFromParcel(Parcel in) {
            return new Agentie(in);
        }

        @Override
        public Agentie[] newArray(int size) {
            return new Agentie[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDataIntalnire() {
        return dataIntalnire;
    }

    public void setDataIntalnire(Date dataIntalnire) {
        this.dataIntalnire = dataIntalnire;
    }

    public String getMarime() {
        return marime;
    }

    public void setMarime(String marime) {
        this.marime = marime;
    }

    public int getNrClienti() {
        return nrClienti;
    }

    public void setNrClienti(int nrClienti) {
        this.nrClienti = nrClienti;
    }

    @Override
    public String toString() {
        return "Agentie{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", dataIntalnire=" + dataIntalnire +
                ", marime='" + marime + '\'' +
                ", nrClienti=" + nrClienti +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nume);
        parcel.writeString(adresa);
        parcel.writeString(marime);
        parcel.writeInt(nrClienti);
        parcel.writeLong(dataIntalnire.getTime());
    }
}
