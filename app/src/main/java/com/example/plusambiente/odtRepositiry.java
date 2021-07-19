package com.example.plusambiente;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class odtRepositiry {
    private desechoDao desechoDao;
    private odtDao odtDao;
    private LiveData<List<odtEntity>> allodt;
    private LiveData<List<desechosEntity>> detalleall;

    public odtRepositiry(Application application){
        PlusRootDataBase db = PlusRootDataBase.getDatabase(application);
        desechoDao = db.desechoDao();
        odtDao = db.odtDao();
        allodt = odtDao.getAll();
        desechoDao = db.desechoDao();
        detalleall = desechoDao.getAll();

    }
    public  LiveData<List<odtEntity>> getAllOdt(){
        return allodt;
    }
    public LiveData<List<desechosEntity>> getAllDetalle(){
        return detalleall;
    }
}
