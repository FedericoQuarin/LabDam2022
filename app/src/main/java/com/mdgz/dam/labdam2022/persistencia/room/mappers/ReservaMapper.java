package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.room.entities.ReservaEntity;

import java.util.Date;

public class ReservaMapper {
    private ReservaMapper() {}

    public static ReservaEntity toEntity(Reserva reserva) {
        return new ReservaEntity(
                reserva.getId(),
                reserva.getFechaIngreso().getTime(),
                reserva.getFechaEgreso().getTime(),
                reserva.getCancelada(),
                reserva.getCantidadPersonas(),
                reserva.getMonto(),
                reserva.getAlojamiento().getId(),
                reserva.getUsuario().getId()
        );
    }

    public static Reserva fromEntity(ReservaEntity reserva) {
        return new Reserva(
                reserva.getId(),
                new Date(reserva.getFechaIngreso()),
                new Date(reserva.getFechaEgreso()),
                reserva.getCantidadPersonas(),
                reserva.getMonto(),
                null,
                null
        );
    }
}
