package com.mdgz.dam.labdam2022.persistencia.factory;

import android.content.Context;

import com.mdgz.dam.labdam2022.persistencia.repositories.AlojamientoRepository;
import com.mdgz.dam.labdam2022.persistencia.room.dataSource.AlojamientoRoomDataSource;

public class AlojamientoRepositoryFactory {
    private AlojamientoRepositoryFactory() {
    }

    public static AlojamientoRepository create(final Context context) {
        return new AlojamientoRepository(new AlojamientoRoomDataSource(context));
    }
}
