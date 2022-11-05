package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

@Dao
public interface CiudadDao {
    @Insert
    void save(Ciudad ciudad);

    @Insert
    void save(List<Ciudad> ciudades);

    @Query("SELECT * FROM CIUDAD")
    List<Ciudad> getList();

    @Query("SELECT * FROM CIUDAD WHERE id = :id")
    Ciudad getCiudad(Integer id);
}
