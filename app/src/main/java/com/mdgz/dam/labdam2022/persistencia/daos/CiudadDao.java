package com.mdgz.dam.labdam2022.persistencia.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

@Dao
public interface CiudadDao {
    @Insert
    void save(Ciudad... ciudad);

    @Query("SELECT * FROM CIUDAD")
    List<Ciudad> getList();
}
