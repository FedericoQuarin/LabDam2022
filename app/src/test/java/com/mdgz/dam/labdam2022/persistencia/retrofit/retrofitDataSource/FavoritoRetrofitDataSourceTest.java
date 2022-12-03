package com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource;

import android.util.Log;
import android.util.Pair;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.UUID;

public class FavoritoRetrofitDataSourceTest {
    private static FavoritoRetrofitDataSource dataSource;
    private static UUID idUsuario;

    @BeforeClass
    public static void init() {

        dataSource = new FavoritoRetrofitDataSource();
        idUsuario = UUID.fromString("da45c247-1b13-4cd3-bca3-a6d86ae6481d");
    }


    @Test
    public void crearFavorito() {
        UUID idAlojamiento = UUID.randomUUID();

        final UUID[] resultado = {UUID.randomUUID(), UUID.randomUUID()};

        dataSource.crearFavorito(idAlojamiento, idUsuario, new OnResult<>() {
            @Override
            public void onSuccess(Favorito result) {
                resultado[0] = result.getIdAlojamiento();
                resultado[1] = result.getIdUsuario();
            }

            @Override
            public void onError(Throwable exception) {
                Assert.fail();
            }
        });

        Assert.assertEquals(idAlojamiento, resultado[0]);
        Assert.assertEquals(idUsuario, resultado[1]);
    }

    @Test
    public void buscarFavorito() {
        dataSource.recuperarFavoritos(idUsuario, new OnResult<LinkedHashSet<UUID>>() {
            @Override
            public void onSuccess(LinkedHashSet<UUID> result) {
                for (UUID id : result) {
                    System.out.println(id.toString());
                }
            }

            @Override
            public void onError(Throwable exception) {
                Assert.fail();
            }
        });
    }

    @Test
    public void eliminarFavorito() {
        UUID idAlojamiento = UUID.randomUUID();

        dataSource.crearFavorito(idAlojamiento, idUsuario, new OnResult<>() {
            @Override
            public void onSuccess(Favorito result) {
                // noop
            }

            @Override
            public void onError(Throwable exception) {
                Assert.fail();
            }
        });

        dataSource.eliminarFavorito(idAlojamiento, new OnResult<>() {
            @Override
            public void onSuccess(Favorito result) {
                // noop
            }

            @Override
            public void onError(Throwable exception) {
                Assert.fail();
            }
        });
    }


    @AfterClass
    public static void finish() {
        dataSource = null;
    }
}
