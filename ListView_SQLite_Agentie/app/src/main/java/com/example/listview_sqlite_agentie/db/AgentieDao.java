package com.example.listview_sqlite_agentie.db;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.listview_sqlite_agentie.Agentie;

import java.util.List;

@Dao
public interface AgentieDao {
    @Query("SELECT * FROM Agentie")
    List<Agentie> getAllAgentii();

    @Insert
    void insertAgentie(Agentie...agentii);
}
