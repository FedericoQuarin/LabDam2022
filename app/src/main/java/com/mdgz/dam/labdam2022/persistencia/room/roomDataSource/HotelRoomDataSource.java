package com.mdgz.dam.labdam2022.persistencia.room.roomDataSource;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.persistencia.dataSources.HotelDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.room.LabDamDatabase;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UbicacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.HotelMapper;

import java.util.ArrayList;
import java.util.List;

public class HotelRoomDataSource implements HotelDataSource {

    private final HotelDAO hotelDAO;
    private final UbicacionDAO ubicacionDAO;
    private final CiudadDAO ciudadDAO;

    public HotelRoomDataSource(Context context) {
        this(LabDamDatabase.getInstance(context));
    }

    public HotelRoomDataSource(LabDamDatabase db) {
        hotelDAO = db.hotelDAO();
        ubicacionDAO = db.ubicacionDAO();
        ciudadDAO = db.ciudadDAO();
    }

    @Override
    public void guardar(Hotel hotel, OnResult<Hotel> callback) {
        try {
            HotelEntity hotelEntity = HotelMapper.toEntity(hotel);
            hotelDAO.guardar(hotelEntity);

            callback.onSuccess(hotel);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void getHoteles(OnResult<List<Hotel>> callback) {
        try {
            List<HotelEntity> listaHotelesEntities = hotelDAO.getHoteles();
            List<Hotel> listaHoteles = new ArrayList<Hotel>();

            UbicacionEntity ubicacionEntity;
            CiudadEntity ciudadEntity;
            for(HotelEntity hotelEntity : listaHotelesEntities) {
                ubicacionEntity = ubicacionDAO.getUbicacionPorId(hotelEntity.getUbicacionId().toString());
                ciudadEntity = ciudadDAO.getCiudadPorId(ubicacionEntity.getCiudadId().toString());

                listaHoteles.add(HotelMapper.fromEntity(hotelEntity, ubicacionEntity, ciudadEntity));
            }

            callback.onSuccess(listaHoteles);
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
