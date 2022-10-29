package com.mdgz.dam.labdam2022.persistencia.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.List;

@Dao
public interface AlojamientoDao {
    @Insert
    void save(Alojamiento... alojamientos);

    @Delete
    void delete(Alojamiento alojamiento);

    @Query("SELECT * FROM ALOJAMIENTO")
    List<Alojamiento> getList();
}
