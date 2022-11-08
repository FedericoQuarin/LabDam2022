package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.room.entities.AlojamientoEntity;

public abstract class AlojamientoMapper {
    private AlojamientoMapper() {
    }

    public static AlojamientoEntity toEntity(final Alojamiento alojamiento) {
        return new AlojamientoEntity(
                alojamiento.getId(),
                alojamiento.getTitulo(),
                alojamiento instanceof Habitacion ? AlojamientoEntity.TIPO_HABITACION : AlojamientoEntity.TIPO_DEPARTAMENTO,
                alojamiento.getDescripcion(),
                alojamiento.getCapacidad(),
                alojamiento.getPrecioBase()
        );
    }
}
