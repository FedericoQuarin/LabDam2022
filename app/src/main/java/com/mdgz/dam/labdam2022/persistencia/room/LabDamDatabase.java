package com.mdgz.dam.labdam2022.persistencia.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.ReservaDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.FavoritoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.ReservaEntity;

import java.util.concurrent.Executors;

@Database(entities = {CiudadEntity.class, AlojamientoEntity.class, ReservaEntity.class, FavoritoEntity.class}, version = 8)
public abstract class LabDamDatabase extends RoomDatabase {
    private static LabDamDatabase instance;

    public abstract CiudadDAO ciudadDao();
    //public abstract AlojamientoDao alojamientoDao();
    public abstract ReservaDAO reservaDao();
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
                                        //getInstance(context).ciudadDao().save(GestorCiudad.ciudadesIniciales());
                                    }
                                }
                        );
                    }
                })
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
