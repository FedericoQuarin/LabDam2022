package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entities.UbicacionEntity;

import java.util.List;

@Dao
public interface UbicacionDAO {
    @Insert
    void guardar(UbicacionEntity ubicacion);

    @Insert
    void guardar(List<UbicacionEntity> ubicaciones);

    @Delete
    void borrar(UbicacionEntity ubicacion);

    @Query("SELECT * FROM UBICACION")
    List<UbicacionEntity> getUbicaciones();
}
