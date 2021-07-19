package com.example.plusambiente;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface desechoDao {
    @Query("select * from detalle_odt")
    LiveData<List<desechosEntity>> getAll();

    @Query("select * from detalle_odt where sol_id= :sol_id")
    desechosEntity findBySol(String sol_id);
   //
}
