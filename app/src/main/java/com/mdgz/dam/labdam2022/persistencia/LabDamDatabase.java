package com.mdgz.dam.labdam2022.persistencia;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.daos.CiudadDao;

@Database(entities = {Ciudad.class}, version = 1)
public abstract class LabDamDatabase extends RoomDatabase {
    public abstract CiudadDao ciudadDao();
}
