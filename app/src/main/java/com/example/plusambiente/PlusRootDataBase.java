package com.example.plusambiente;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {desechosEntity.class,odtEntity.class}, version = 1)
public abstract class PlusRootDataBase extends RoomDatabase {
    public abstract desechoDao desechoDao();
    public abstract odtDao odtDao();
    private static volatile PlusRootDataBase INSTANCE;
    public static PlusRootDataBase getDatabase(final Context context) {
        if(INSTANCE == null){
            synchronized (PlusRootDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlusRootDataBase.class, "plus")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
