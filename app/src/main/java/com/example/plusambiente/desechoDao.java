package com.example.plusambiente;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface desechoDao {
    @Query("select * from odt")
    LiveData<List<desechosEntity>> getAll();
}
