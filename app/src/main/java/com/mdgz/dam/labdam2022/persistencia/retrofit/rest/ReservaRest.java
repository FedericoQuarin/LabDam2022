package com.mdgz.dam.labdam2022.persistencia.retrofit.rest;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.ReservaEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReservaRest {
    @POST("reserva/")
    Call<Reserva> guardarReserva(@Body ReservaEntity reserva);
}
