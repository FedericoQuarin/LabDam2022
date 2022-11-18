package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UbicacionEntity;

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

    public static Habitacion fromEntity(HabitacionEntity habitacion, AlojamientoEntity alojamiento, HotelEntity hotel, UbicacionEntity ubicacion, CiudadEntity ciudad) {
        return new Habitacion(
                habitacion.getIdHabitacion(),
                alojamiento.getTitulo(),
                alojamiento.getDescripcion(),
                alojamiento.getCapacidad(),
                alojamiento.getPrecioBase(),
                habitacion.getCamasIndividuales(),
                habitacion.getCamasMatrimoniales(),
                habitacion.getTieneEstacionamiento(),
                HotelMapper.fromEntity(hotel, ubicacion, ciudad),
                false
        );
    }
}
