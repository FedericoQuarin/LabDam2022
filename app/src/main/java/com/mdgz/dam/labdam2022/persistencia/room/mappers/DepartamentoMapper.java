package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import android.util.Log;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.persistencia.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UbicacionEntity;

import java.util.UUID;

public class DepartamentoMapper {
    private DepartamentoMapper() {
    }

    public static DepartamentoEntity toEntity(final Departamento departamento) {
        return new DepartamentoEntity(
                departamento.getId(),
                departamento.getTieneWifi(),
                departamento.getCostoLimpieza(),
                departamento.getCantidadHabitaciones(),
                departamento.getId(),
                departamento.getUbicacion().getId()
        );
    }

    public static Departamento fromEntity(DepartamentoEntity departamento, AlojamientoEntity alojamiento, UbicacionEntity ubicacion, CiudadEntity ciudad) {
        return new Departamento(
                departamento.getIdDepartamento(),
                alojamiento.getTitulo(),
                alojamiento.getDescripcion(),
                alojamiento.getCapacidad(),
                alojamiento.getPrecioBase(),
                departamento.getTieneWifi(),
                departamento.getCostoLimpieza(),
                departamento.getCantidadHabitaciones(),
                UbicacionMapper.fromEntity(ubicacion, ciudad),
                false
        );
    }
}
