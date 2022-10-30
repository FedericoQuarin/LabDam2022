package com.mdgz.dam.labdam2022.persistencia;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.daos.AlojamientoDao;
import com.mdgz.dam.labdam2022.persistencia.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.daos.ReservaDao;

@Database(entities = {Ciudad.class, Alojamiento.class, Reserva.class, Favorito.class}, version = 4)
public abstract class LabDamDatabase extends RoomDatabase {
    public abstract CiudadDao ciudadDao();
    public abstract AlojamientoDao alojamientoDao();
    public abstract ReservaDao reservaDao();
    public abstract FavoritoDao favoritoDao();

}
