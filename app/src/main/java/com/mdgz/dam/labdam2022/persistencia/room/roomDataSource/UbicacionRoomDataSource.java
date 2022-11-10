package com.mdgz.dam.labdam2022.persistencia.room.roomDataSource;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.dataSources.UbicacionDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.LabDamDatabase;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UbicacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.CiudadMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.UbicacionMapper;

import java.util.ArrayList;
import java.util.List;

public class UbicacionRoomDataSource implements UbicacionDataSource {

    private final UbicacionDAO ubicacionDAO;
    private final CiudadDAO ciudadDAO;

    public UbicacionRoomDataSource(Context context) {this(LabDamDatabase.getInstance(context));}

    public UbicacionRoomDataSource(LabDamDatabase db) {
        ubicacionDAO = db.ubicacionDAO();
        ciudadDAO = db.ciudadDAO();
    }

    @Override
    public void guardar(Ubicacion ubicacion, OnResult<Ubicacion> callback) {
        try {
            UbicacionEntity ubicacionEntity = UbicacionMapper.toEntity(ubicacion);
            ubicacionDAO.guardar(ubicacionEntity);

            callback.onSuccess(ubicacion);

        } catch (Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void getUbicaciones(OnResult<List<Ubicacion>> callback) {
        try {
            List<UbicacionEntity> listaUbicacionesEntities = ubicacionDAO.getUbicaciones();
            List<Ubicacion> listaUbicaciones = new ArrayList<Ubicacion>();

            CiudadEntity ciudad;
            for(UbicacionEntity ubicacionEntity : listaUbicacionesEntities) {
                ciudad = ciudadDAO.getCiudadPorId(ubicacionEntity.getCiudadId().toString());
                listaUbicaciones.add(UbicacionMapper.fromEntity(ubicacionEntity, ciudad));
            }

            callback.onSuccess(listaUbicaciones);

        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
