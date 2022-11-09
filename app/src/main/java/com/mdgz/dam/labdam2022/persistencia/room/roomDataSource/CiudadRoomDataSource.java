package com.mdgz.dam.labdam2022.persistencia.room.roomDataSource;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.dataSources.CiudadDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.room.LabDamDatabase;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.CiudadMapper;

import java.util.ArrayList;
import java.util.List;

public class CiudadRoomDataSource implements CiudadDataSource {

    private final CiudadDAO ciudadDAO;

    public CiudadRoomDataSource(Context context) {
        this(LabDamDatabase.getInstance(context));
    }

    public CiudadRoomDataSource(LabDamDatabase db) {
        ciudadDAO = db.ciudadDAO();
    }

    @Override
    public void guardar(Ciudad ciudad, OnResult<Ciudad> callback) {
        try {
            CiudadEntity ciudadEntity = CiudadMapper.toEntity(ciudad);
            ciudadDAO.guardar(ciudadEntity);

            callback.onSuccess(ciudad);

        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void getCiudades(OnResult<List<Ciudad>> callback) {
        try {
            List<CiudadEntity> listaCiudadesEntity = ciudadDAO.getCiudades();
            List<Ciudad> listaCiudades = new ArrayList<Ciudad>();

            for(CiudadEntity ciudadEntity : listaCiudadesEntity) {
                listaCiudades.add(CiudadMapper.fromEntity(ciudadEntity));
            }
             callback.onSuccess(listaCiudades);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }
}
