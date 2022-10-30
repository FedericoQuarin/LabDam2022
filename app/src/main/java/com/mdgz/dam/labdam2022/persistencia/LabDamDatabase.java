package com.mdgz.dam.labdam2022.persistencia;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mdgz.dam.labdam2022.gestores.GestorCiudad;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.daos.AlojamientoDao;
import com.mdgz.dam.labdam2022.persistencia.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.daos.ReservaDao;

import java.util.concurrent.Executors;

@Database(entities = {Ciudad.class, Alojamiento.class, Reserva.class, Favorito.class}, version = 4)
public abstract class LabDamDatabase extends RoomDatabase {
    private static LabDamDatabase instance;

    public abstract CiudadDao ciudadDao();
    //public abstract AlojamientoDao alojamientoDao();
    //public abstract ReservaDao reservaDao();
    //public abstract FavoritoDao favoritoDao();

    public synchronized static LabDamDatabase getInstance(Context context){
        if (instance == null) {
            instance = buildDatabase(context);
        }
        return instance;
    }

    private static LabDamDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, LabDamDatabase.class, "labdam_db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        getInstance(context).ciudadDao().save(GestorCiudad.ciudadesIniciales());
                                    }
                                }
                        );
                    }
                })
                .allowMainThreadQueries()
                .build();
    }
}
