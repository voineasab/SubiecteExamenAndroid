package com.example.listview_sqlite_agentie.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.listview_sqlite_agentie.Agentie;
import com.example.listview_sqlite_agentie.DateConverter;

@Database(entities =  {Agentie.class}, version = 3)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract AgentieDao agentieDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"DATABASE").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
