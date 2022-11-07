package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entities.AlojamientoEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface AlojamientoDao {
    @Insert
    void insertar(AlojamientoEntity... alojamientos);

    @Delete
    void delete(AlojamientoEntity alojamiento);

    @Query("SELECT * FROM ALOJAMIENTO")
    List<AlojamientoEntity> getAlojamientos();

    @Query("SELECT * FROM ALOJAMIENTO WHERE id=:alojamientoId")
    AlojamientoEntity getAlojamientoPorId(UUID alojamientoId);
}
