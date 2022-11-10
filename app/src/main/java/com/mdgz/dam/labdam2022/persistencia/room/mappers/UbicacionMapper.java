package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UbicacionEntity;

public class UbicacionMapper {
    private UbicacionMapper() {}

    public static UbicacionEntity toEntity (Ubicacion ubicacion) {
        return new UbicacionEntity(
                ubicacion.getId(),
                ubicacion.getLat(),
                ubicacion.getLng(),
                ubicacion.getCalle(),
                ubicacion.getNumero(),
                ubicacion.getCiudad().getId()
        );
    }

    public static Ubicacion fromEntity (UbicacionEntity ubicacion, CiudadEntity ciudad) {
        return new Ubicacion(
                ubicacion.getId(),
                ubicacion.getLat(),
                ubicacion.getLng(),
                ubicacion.getCalle(),
                ubicacion.getNumero(),
                CiudadMapper.fromEntity(ciudad)
        );
    }
}
