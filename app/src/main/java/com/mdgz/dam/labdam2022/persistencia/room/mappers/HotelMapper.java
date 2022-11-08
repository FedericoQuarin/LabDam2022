package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HotelEntity;

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

    public static Hotel fromEntity (HotelEntity hotel) {
        return new Hotel(
                hotel.getId(),
                hotel.getNombre(),
                hotel.getCategoria(),
                null
        );
    }
}
