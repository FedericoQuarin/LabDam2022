package com.mdgz.dam.labdam2022.room;

import android.content.Context;

import androidx.room.Room;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

public class CiudadRepository {
    private static CiudadRepository _CIUDADREPO = null;
    private CiudadDao ciudadDao;

    private CiudadRepository(Context ctx){
        LabDamDatabase db = Room.databaseBuilder(ctx,
                        LabDamDatabase.class, "dam_db").fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        ciudadDao= db.ciudadDao();
    }
    public static CiudadRepository getInstance(Context ctx){
        if(_CIUDADREPO==null) _CIUDADREPO = new CiudadRepository(ctx);
        return _CIUDADREPO;
    }

    public void save(Ciudad... ciudad) {
        ciudadDao.save(ciudad);
    }

    public List<Ciudad> getList() {
        return ciudadDao.getList();
    }
}
