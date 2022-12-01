package com.mdgz.dam.labdam2022.persistencia.factories;

import android.content.Context;

import com.mdgz.dam.labdam2022.persistencia.repositories.ReservaRepository;
import com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource.ReservaRetrofitDataSource;

public class ReservaRepositoryFactory {
    private ReservaRepositoryFactory() {
    }

    public static ReservaRepository create(final Context context) {
        return new ReservaRepository(new ReservaRetrofitDataSource(context));
    }
}
