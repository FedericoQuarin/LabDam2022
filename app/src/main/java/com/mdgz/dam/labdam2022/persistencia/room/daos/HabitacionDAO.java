package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entities.HabitacionEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface HabitacionDAO {
    @Insert
    void guardar(HabitacionEntity habitacion);

    @Insert
    void guardar(List<HabitacionEntity> habitaciones);

    @Delete
    void borrar(HabitacionEntity habitacion);

    @Query("SELECT * FROM HABITACION")
    List<HabitacionEntity> getHabitaciones();

    @Query("SELECT * FROM HABITACION where id=:habitacionId")
    HabitacionEntity getHabitacionPorId(UUID habitacionId);

    @Query("SELECT * FROM HABITACION where alojamiento_id=:alojamientoId")
    HabitacionEntity getHabitacionPorIdAlojamiento(UUID alojamientoId);
}
