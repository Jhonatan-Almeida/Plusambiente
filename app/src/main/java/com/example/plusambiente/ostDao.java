package com.example.plusambiente;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ostDao {
    @Query("select * from detalle_odt")
    LiveData<List<odtEntity>> getAll();
}
