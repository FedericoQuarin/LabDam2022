package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;

public class CiudadMapper {
    private CiudadMapper() {}

    public static CiudadEntity toEntity (Ciudad ciudad) {
        return new CiudadEntity(
                ciudad.getId(),
                ciudad.getNombre(),
                ciudad.getAbreviatura()
        );
    }

    public static Ciudad fromEntity (CiudadEntity ciudad) {
        return new Ciudad(
                ciudad.getId(),
                ciudad.getNombre(),
                ciudad.getAbreviatura()
        );
    }
}
