package com.mdgz.dam.labdam2022.persistencia.repositories;

import android.util.Log;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.dataSources.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;

import java.util.List;
import java.util.UUID;

// Aca decimos que el repository implementa el ds para de forma
// facil nos exponga todos los metodos pero no es necesario y podría
// no ser lo que buscamos ( ej: exponer menos metodos o componer metodos del o los datasource)
public class AlojamientoRepository implements AlojamientoDataSource {

    private final AlojamientoDataSource alojamientoDataSource;
    private final FavoritoDataSource favoritoDataSource;

    public AlojamientoRepository(
            final AlojamientoDataSource alojamientoDataSource,
            final FavoritoDataSource favoritoDataSource) {
        this.alojamientoDataSource = alojamientoDataSource;
        this.favoritoDataSource = favoritoDataSource;
    }

    @Override
    public void guardarHabitacion(Habitacion habitacion, OnResult<Habitacion> callback) {
        alojamientoDataSource.guardarHabitacion(habitacion, callback);
    }

    @Override
    public void guardarDepartamento(Departamento departamento, OnResult<Departamento> callback) {
        alojamientoDataSource.guardarDepartamento(departamento, callback);
    }

    @Override
    public void recuperarHabitaciones(OnResult<List<Habitacion>> callback) {
        alojamientoDataSource.recuperarHabitaciones(callback);
    }

    @Override
    public void recuperarDepartamentos(OnResult<List<Departamento>> callback) {
        alojamientoDataSource.recuperarDepartamentos(callback);
    }

    @Override
    public void recuperarAlojamientos(OnResult<List<Alojamiento>> callback) {
        alojamientoDataSource.recuperarAlojamientos(callback);
    }

    @Override
    public void recuperarAlojamiento(UUID idAlojamiento, OnResult<Alojamiento> callback) {
        alojamientoDataSource.recuperarAlojamiento(idAlojamiento, callback);
    }

    public void colocarFavorito(UUID idAlojamiento, UUID idUsuario, OnResult<UUID> callback) {
        favoritoDataSource.crearFavorito(idAlojamiento, idUsuario, new OnResult<>() {
            @Override
            public void onSuccess(Favorito result) {
                callback.onSuccess(result.getIdAlojamiento());
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(exception);
            }
        });

        //Log.e("AlojamientoRespository", "Persistencia favorito no implementada");
    }

    public void quitarFavorito(UUID idAlojamiento, UUID idUsuario, OnResult<UUID> callback) {
        favoritoDataSource.eliminarFavorito(idAlojamiento, new OnResult<>() {
            @Override
            public void onSuccess(Favorito result) {
                callback.onSuccess(idAlojamiento);
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(exception);
            }
        });

        //Log.e("AlojamientoRespository", "Persistencia favorito no implementada");
    }
}
