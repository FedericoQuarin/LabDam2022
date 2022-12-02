package com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource;

import android.util.Pair;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

public class FavoritoRetrofitDataSourceTest {
    private static FavoritoRetrofitDataSource dataSource;

    @BeforeClass
    public static void init() {
        dataSource = new FavoritoRetrofitDataSource();
    }


    @Test
    public void crearFavorito() {
        UUID idAlojamiento = UUID.randomUUID();
        UUID idUsuario = UUID.randomUUID();

        final UUID[] resultado = {UUID.randomUUID(), UUID.randomUUID()};

        dataSource.crearFavorito(idAlojamiento, idUsuario, new OnResult<>() {
            @Override
            public void onSuccess(Pair<UUID, UUID> result) {
                resultado[0] = result.first;
                resultado[1] = result.second;
            }

            @Override
            public void onError(Throwable exception) {
                Assert.fail();
            }
        });

        try {
            Thread.sleep(4000);
        }
        catch (InterruptedException exc) {
            exc.printStackTrace();
        }

        Assert.assertEquals(idAlojamiento, resultado[0]);
        Assert.assertEquals(idUsuario, resultado[1]);
    }


    @AfterClass
    public static void finish() {
        dataSource = null;
    }
}
