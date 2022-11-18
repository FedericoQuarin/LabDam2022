package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UbicacionEntity;

public class HotelMapper {
    private HotelMapper() {}

    public static HotelEntity toEntity (Hotel hotel) {
        return new HotelEntity(
                hotel.getId(),
                hotel.getNombre(),
                hotel.getCategoria(),
                hotel.getUbicacion().getId()
        );
    }

    public static Hotel fromEntity (HotelEntity hotel, UbicacionEntity ubicacion, CiudadEntity ciudad) {
        return new Hotel(
                hotel.getId(),
                hotel.getNombre(),
                hotel.getCategoria(),
                UbicacionMapper.fromEntity(ubicacion, ciudad)
        );
    }
}
