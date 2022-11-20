package com.mdgz.dam.labdam2022.persistencia.retrofit.mappers;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.ReservaEntity;

public class ReservaMapper {
    public static Reserva toModelClass(ReservaEntity entity) {
        Reserva reserva = new Reserva(entity.getUsuarioId(),
                entity.getFechaIngreso(),
                entity.getFechaSalida(),
                null,
                null,
                null,
                null);
        return reserva;
    }

    public static ReservaEntity toEntity(Reserva reserva) {
        ReservaEntity entity = new ReservaEntity(reserva.getAlojamiento().getId(),
                reserva.getUsuario().getId(),
                reserva.getFechaIngreso(),
                reserva.getFechaEgreso());

        return entity;
    }
}
