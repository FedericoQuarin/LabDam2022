package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.room.entities.ReservaEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UsuarioEntity;

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

    // TODO: recuperar alojamiento dependiendo si es depto o habitacion
    public static Reserva fromEntity(ReservaEntity reserva, UsuarioEntity usuario) {
        return new Reserva(
                reserva.getId(),
                new Date(reserva.getFechaIngreso()),
                new Date(reserva.getFechaEgreso()),
                reserva.getCantidadPersonas(),
                reserva.getMonto(),
                null,
                UsuarioMapper.fromEntity(usuario)
        );
    }
}
