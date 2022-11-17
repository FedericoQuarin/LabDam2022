package com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource;

import android.util.Log;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.dataSources.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.RetrofitConfig;
import com.mdgz.dam.labdam2022.persistencia.retrofit.mappers.ReservaMapper;
import com.mdgz.dam.labdam2022.persistencia.retrofit.rest.ReservaRest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class ReservaRetrofitDataSource implements ReservaDataSource {
    final private ReservaRest reservaRest;

    public ReservaRetrofitDataSource() {
        RetrofitConfig retrofitConfig = RetrofitConfig.getInstance();

        reservaRest = retrofitConfig.reservaRest;
    }

    @Override
    public void guardarReserva(Reserva reserva, OnResult<Reserva> callback) {
        Call<Reserva> call = reservaRest.guardarReserva(ReservaMapper.toEntity(reserva));
        Response<Reserva> res = null;
        try {
            res = call.execute();
            if (!res.isSuccessful()) Log.e("ReservaRetrofit", "Fallo guardar reserva");
        } catch (IOException e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarReservas(OnResult<List<Reserva>> callback) {

    }

    @Override
    public void recuperarReserva(UUID id, OnResult<List<Reserva>> callback) {

    }
}
