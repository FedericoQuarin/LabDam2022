package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HabitacionEntity;

public class HabitacionMapper {

    private HabitacionMapper() {
    }

    public static HabitacionEntity toEntity(final Habitacion habitacion) {
        return new HabitacionEntity(
                habitacion.getId(),
                habitacion.getCamasIndividuales(),
                habitacion.getCamasMatrimoniales(),
                habitacion.getTieneEstacionamiento(),
                habitacion.getId(),
                habitacion.getHotel().getId()
        );
    }

    public static Habitacion fromEntity(HabitacionEntity habitacion, AlojamientoEntity alojamiento) {
        return new Habitacion(
                habitacion.getIdHabitacion(),
                alojamiento.getTitulo(),
                alojamiento.getDescripcion(),
                alojamiento.getCapacidad(),
                alojamiento.getPrecioBase(),
                habitacion.getCamasIndividuales(),
                habitacion.getCamasMatrimoniales(),
                habitacion.getTieneEstacionamiento(),
                null, // TODO: Agregar hotel
                null
        );
    }
}
