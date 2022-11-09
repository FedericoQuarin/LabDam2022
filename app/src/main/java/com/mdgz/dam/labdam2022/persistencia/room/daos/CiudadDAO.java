package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;

import java.util.List;

@Dao
public interface CiudadDAO {
    @Insert
    void guardar(CiudadEntity ciudad);

    @Query("SELECT * FROM CIUDAD")
    List<CiudadEntity> getCiudades();

    @Query("SELECT * FROM CIUDAD WHERE id = :id")
    CiudadEntity getCiudadPorId(Integer id);
}
