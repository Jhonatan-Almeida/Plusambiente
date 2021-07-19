package com.example.plusambiente;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface odtDao {
    @Query("select * from odt")
    LiveData<List<odtEntity>> getAll();

    /*@Query("select * from odt where = :identificador")
    desechosEntity findBySol(String sol_id);*/
}
