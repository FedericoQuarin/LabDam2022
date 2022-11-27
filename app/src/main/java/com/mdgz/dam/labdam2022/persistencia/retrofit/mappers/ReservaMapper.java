package com.mdgz.dam.labdam2022.persistencia.retrofit.mappers;

import androidx.annotation.NonNull;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.ReservaEntity;

public class ReservaMapper {
    public static Reserva toModelClass(@NonNull ReservaEntity entity, Alojamiento alojamiento, Usuario usuario) {
        return new Reserva(entity.getUsuarioId(),
                entity.getFechaIngreso(),
                entity.getFechaSalida(),
                null,
                null,
                alojamiento,
                usuario);
    }

    public static ReservaEntity toEntity(@NonNull Reserva reserva) {
        return new ReservaEntity(reserva.getAlojamiento().getId(),
                reserva.getUsuario().getId(),
                reserva.getFechaIngreso(),
                reserva.getFechaEgreso());
    }
}
