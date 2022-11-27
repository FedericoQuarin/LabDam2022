package com.mdgz.dam.labdam2022.persistencia.retrofit.rest;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.ReservaEntity;

import java.util.List;
import java.util.UUID;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReservaRest {
    @POST("reserva")
    Call<ReservaEntity> guardarReserva(@Body ReservaEntity reserva);

    @GET("reserva")
    Call<List<ReservaEntity>> buscarReservas();

    @DELETE("reserva")
    Call<ResponseBody> eliminarReserva(@Query("alojamientoId") UUID alojamientoId);
}
