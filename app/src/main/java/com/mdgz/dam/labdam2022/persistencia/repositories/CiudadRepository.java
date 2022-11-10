package com.mdgz.dam.labdam2022.persistencia.repositories;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.dataSources.CiudadDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDAO;
import com.mdgz.dam.labdam2022.persistencia.room.LabDamDatabase;

import java.util.List;

public class CiudadRepository implements CiudadDataSource{

    private final CiudadDataSource ciudadDataSource;

    public CiudadRepository(CiudadDataSource dataSource) { this.ciudadDataSource = dataSource; }

    @Override
    public void guardar(Ciudad ciudad, OnResult<Ciudad> callback) {
        ciudadDataSource.guardar(ciudad, callback);
    }

    @Override
    public void getCiudades(OnResult<List<Ciudad>> callback) {
        ciudadDataSource.getCiudades(callback);
    }

    /*private static CiudadRepository _CIUDADREPO = null;
    private CiudadDAO ciudadDao;

    private CiudadRepository(Context ctx){
        LabDamDatabase db = LabDamDatabase.getInstance(ctx);
        ciudadDao= db.ciudadDAO();
    }
    public static CiudadRepository getInstance(Context ctx){
        if(_CIUDADREPO==null) _CIUDADREPO = new CiudadRepository(ctx);
        return _CIUDADREPO;
    }

    public void guardar(Ciudad ciudad) {
        ciudadDao.guardar(ciudad);
    }

    public void guardar(List<Ciudad> ciudades) {
        ciudadDao.guardar(ciudades);
    }

    public List<Ciudad> getList() {
        return ciudadDao.getCiudades();
    }

    public Ciudad getCiudad(Integer id) {
        return ciudadDao.getCiudadPorId(id);
    }*/
}
