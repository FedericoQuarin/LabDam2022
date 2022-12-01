package com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.retrofit.RetrofitConfig;
import com.mdgz.dam.labdam2022.persistencia.retrofit.rest.ReservaRest;
import com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource.ReservaRetrofitDataSource;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReservaRetrofitDataSourceTest {
    private static ReservaRetrofitDataSource dataSource;

    @BeforeClass
    public static void init() {
        dataSource = new ReservaRetrofitDataSource(null);
    }

    @Test
    public void buscarReservas() {
        final List<Reserva> reservas = new ArrayList<>();
        OnResult<List<Reserva>> callback = new OnResult<>() {
            @Override
            public void onSuccess(List<Reserva> result) {
                reservas.addAll(result);
            }

            @Override
            public void onError(Throwable exception) {
                exception.printStackTrace();
                Assert.fail();
            }
        };

        dataSource.recuperarReservas(callback);

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException exc) {
            exc.printStackTrace();
        }

        System.out.println(reservas);


    }

}
