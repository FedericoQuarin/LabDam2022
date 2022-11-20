package com.mdgz.dam.labdam2022.persistencia.retrofit.rest;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.retrofit.RetrofitConfig;
import com.mdgz.dam.labdam2022.persistencia.retrofit.mappers.ReservaMapper;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class ReservaRestTest {
    private static ReservaRest reservaRest;

    @BeforeClass
    public static void init() {
         reservaRest = RetrofitConfig.getInstance().reservaRest;
    }

    @Test
    public void buscarReservas() throws IOException {
        Call<List<Reserva>> reservaCall = reservaRest.buscarReservas();
        Response<List<Reserva>> response = reservaCall.execute();
        if (response.isSuccessful()) {
            System.out.println(response.body());
        } else {
            Assert.fail();
        }
    }

    @Test
    public void guardarReserva() throws IOException {
        Reserva reserva = new Reserva(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDateTime.of(2022, 11, 29, 12, 0).toInstant(ZoneOffset.UTC)),
                1,
                100.0,
                new Departamento(),
                new Usuario());

        Call<Reserva> reservaCall = reservaRest.guardarReserva(ReservaMapper.toEntity(reserva));
        Response<Reserva> response = reservaCall.execute();
        if (response.isSuccessful()) {
            System.out.println(response.body());
        } else {
            System.out.println(response.code());
            Assert.fail();
        }
    }

    @Test
    public void borrarReserva() throws IOException {
        Departamento departamento = new Departamento();
        UUID idAlojamiento = departamento.getId();

        Reserva reserva = new Reserva(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDateTime.of(2022, 11, 29, 12, 0).toInstant(ZoneOffset.UTC)),
                1,
                100.0,
                departamento,
                new Usuario());

        Call<Reserva> guardarReservaCall = reservaRest.guardarReserva(ReservaMapper.toEntity(reserva));
        Response<Reserva> guardarReservaResponse = guardarReservaCall.execute();

        if (!guardarReservaResponse.isSuccessful()) Assert.fail("Fallo guardarReserva");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Assert.fail("Ni idea");
        }

        Call<okhttp3.ResponseBody> borrarReservaCall = reservaRest.eliminarReserva(idAlojamiento);
        Response<okhttp3.ResponseBody> borrarReservaResponse = borrarReservaCall.execute();

        if (!borrarReservaResponse.isSuccessful()) {
            Assert.fail("No se pudo borrar la reserva. Codigo: " + borrarReservaResponse.code());
        }

        Call<List<Reserva>> buscarReservaCall = reservaRest.buscarReservas();
        List<Reserva> reservas = buscarReservaCall.execute().body();

        for (Reserva r : reservas) {
            if (r.getId().equals(idAlojamiento)) Assert.fail("No se borro la reserva");
        }
    }
}
