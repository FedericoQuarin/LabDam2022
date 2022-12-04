package com.mdgz.dam.labdam2022.persistencia.factories;

import android.content.Context;

import com.mdgz.dam.labdam2022.persistencia.repositories.AlojamientoRepository;
import com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource.FavoritoRetrofitDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.AlojamientoRoomDataSource;

public class AlojamientoRepositoryFactory {
    private AlojamientoRepositoryFactory() {
    }

    public static AlojamientoRepository create(final Context context) {
        return new AlojamientoRepository(
                new AlojamientoRoomDataSource(context),
                new FavoritoRetrofitDataSource());
    }
}
