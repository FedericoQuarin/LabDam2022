package com.mdgz.dam.labdam2022.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

@Dao
public interface CiudadDao {
    @Insert
    public void save(Ciudad... ciudad);

    @Query("SELECT * FROM CIUDAD")
    public List<Ciudad> getList();
}
