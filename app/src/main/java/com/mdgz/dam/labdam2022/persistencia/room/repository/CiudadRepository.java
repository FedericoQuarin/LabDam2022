package com.mdgz.dam.labdam2022.persistencia.room.repository;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDAO;
import com.mdgz.dam.labdam2022.persistencia.room.LabDamDatabase;

import java.util.List;

public class CiudadRepository {
    private static CiudadRepository _CIUDADREPO = null;
    private CiudadDAO ciudadDao;

    private CiudadRepository(Context ctx){
        LabDamDatabase db = LabDamDatabase.getInstance(ctx);
        ciudadDao= db.ciudadDao();
    }
    public static CiudadRepository getInstance(Context ctx){
        if(_CIUDADREPO==null) _CIUDADREPO = new CiudadRepository(ctx);
        return _CIUDADREPO;
    }

    public void save(Ciudad ciudad) {
        ciudadDao.save(ciudad);
    }

    public void save(List<Ciudad> ciudades) {
        ciudadDao.save(ciudades);
    }

    public List<Ciudad> getList() {
        return ciudadDao.getList();
    }

    public Ciudad getCiudad(Integer id) {
        return ciudadDao.getCiudad(id);
    }
}
