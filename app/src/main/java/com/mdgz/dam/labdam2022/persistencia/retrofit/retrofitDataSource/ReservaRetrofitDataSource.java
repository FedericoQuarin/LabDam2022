package com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource;

import android.content.Context;

import com.mdgz.dam.labdam2022.exceptions.EntidadNoEncontradaException;
import com.mdgz.dam.labdam2022.exceptions.ProblemaConApiException;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.dataSources.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.RetrofitConfig;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.ReservaEntity;
import com.mdgz.dam.labdam2022.persistencia.retrofit.mappers.ReservaMapper;
import com.mdgz.dam.labdam2022.persistencia.retrofit.rest.ReservaRest;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.AlojamientoRoomDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class ReservaRetrofitDataSource implements ReservaDataSource {
    private ReservaRest reservaRest;
    private AlojamientoRoomDataSource alojamientoRoomDataSource;

    // Se buscan los alojamiento en Room, debido a que no es posible guardaros en la API
    // Solo para eso se utiliza Context
    public ReservaRetrofitDataSource(Context ctx) {
        RetrofitConfig retrofitConfig = RetrofitConfig.getInstance();
        alojamientoRoomDataSource = new AlojamientoRoomDataSource(ctx);

        reservaRest = retrofitConfig.reservaRest;
    }

    // Para hacer parte de las prueba se utiliza este constructor
    // No utilizar en la app
    /*public ReservaRetrofitDataSource() {
        RetrofitConfig retrofitConfig = RetrofitConfig.getInstance();

        reservaRest = retrofitConfig.reservaRest;
    }*/

    @Override
    public void guardarReserva(Reserva reserva, OnResult<Reserva> callback) {
        //Call<ReservaEntity>
    }

    // Este metodo devuelve todas las reservas existentes para un usuario dado
    // TODO logica de usuario
    @Override
    public void recuperarReservas(OnResult<List<Reserva>> callback) {
        List<ReservaEntity> reservasEntity;
        List<Reserva> reservas = new ArrayList<>();

        // Se crea el call al API REST
        Call<List<ReservaEntity>> reservaCall = reservaRest.buscarReservas();
        try{
            // Se ejecuta el llamado. Puede lanzar IOException en caso de que no se pueda conectar a la API
            Response<List<ReservaEntity>> response = reservaCall.execute();

            // Si el codigo de respuesta es exitoso se procede a realizar la logica para devolver las reservas
            if (response.isSuccessful() && response.body() != null) {
                reservasEntity = new ArrayList<>(response.body());

                HashMap<UUID, Alojamiento> alojamientosCache = new HashMap<>();     // Mapa usado como cache para no buscar alojamientos repetidos
                for (ReservaEntity r : reservasEntity) {
                    Alojamiento alojamiento;

                    if (!alojamientosCache.containsKey(r.getAlojamientoId())) {
                        alojamientoRoomDataSource.recuperarAlojamiento(r.getAlojamientoId(), new OnResult<>() {
                            @Override
                            public void onSuccess(Alojamiento result) {
                                alojamientosCache.put(result.getId(), result);
                            }

                            @Override
                            public void onError(Throwable exception) {
                                callback.onError(exception);
                            }
                        });
                    }
                    alojamiento = alojamientosCache.get(r.getAlojamientoId());

                    reservas.add(ReservaMapper.toModelClass(r, alojamiento, null));
                }

                callback.onSuccess(reservas);
            }
            else {
                // TODO ver tema excepciones de respuesta
                callback.onError(new ProblemaConApiException(response.code()));
            }
        }
        catch (IOException exc) {
            callback.onError(exc);
        }
    }

    // Este metodo llama al metodo recuperarReservas y devuelve la reserva con el id dado
    // Si no se encuentra una reserva con dicho id, devuelve EntidadNoEncontradaException
    @Override
    public void recuperarReserva(UUID id, OnResult<Reserva> callback) {
        this.recuperarReservas(new OnResult<>() {
            @Override
            public void onSuccess(List<Reserva> result) {
                for (Reserva r : result) {
                    if (r.getId().equals(id)) callback.onSuccess(r);
                }

                callback.onError(new EntidadNoEncontradaException("Reserva", id));
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(exception);
            }
        });
    }

    @Override
    public void eliminarReserva(UUID id, OnResult<Reserva> callback) {

    }
}
