package com.mdgz.dam.labdam2022.persistencia.factory;

import android.content.Context;

import com.mdgz.dam.labdam2022.persistencia.repositories.CiudadRepository;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.CiudadRoomDataSource;

public class CiudadRepositoryFactory {
    private CiudadRepositoryFactory() {}

    public static CiudadRepository create(Context context) {
        return new CiudadRepository(new CiudadRoomDataSource(context));
    }
}
