package com.mdgz.dam.labdam2022.persistencia.repositories;

import android.util.Log;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.dataSources.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

// Aca decimos que el repository implementa el ds para de forma
// facil nos exponga todos los metodos pero no es necesario y podría
// no ser lo que buscamos ( ej: exponer menos metodos o componer metodos del o los datasource)
public class AlojamientoRepository {

    private final AlojamientoDataSource alojamientoDataSource;
    private final FavoritoDataSource favoritoDataSource;

    public AlojamientoRepository(
            final AlojamientoDataSource alojamientoDataSource,
            final FavoritoDataSource favoritoDataSource) {
        this.alojamientoDataSource = alojamientoDataSource;
        this.favoritoDataSource = favoritoDataSource;
    }

    public void guardarHabitacion(Habitacion habitacion, OnResult<Habitacion> callback) {
        alojamientoDataSource.guardarHabitacion(habitacion, callback);
    }

    public void guardarDepartamento(Departamento departamento, OnResult<Departamento> callback) {
        alojamientoDataSource.guardarDepartamento(departamento, callback);
    }

    public void recuperarHabitaciones(OnResult<List<Habitacion>> callback) {
        alojamientoDataSource.recuperarHabitaciones(callback);
    }

    public void recuperarDepartamentos(OnResult<List<Departamento>> callback) {
        alojamientoDataSource.recuperarDepartamentos(callback);
    }

    public void recuperarAlojamientos(UUID idUsuario, OnResult<List<Alojamiento>> callback) {
        final List<Alojamiento> alojamientos = new ArrayList<>();
        alojamientoDataSource.recuperarAlojamientos(new OnResult<>() {
            @Override
            public void onSuccess(List<Alojamiento> result) {
                alojamientos.addAll(result);
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(exception);
            }
        });
        final LinkedHashSet<UUID> favoritos = new LinkedHashSet<>();
        favoritoDataSource.recuperarFavoritos(idUsuario, new OnResult<>() {
            @Override
            public void onSuccess(LinkedHashSet<UUID> result) {
                favoritos.addAll(result);
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(exception);
            }
        });

        for (Alojamiento a : alojamientos) {
            if (favoritos.contains(a.getId())) a.setEsFavorito(true);
        }

        callback.onSuccess(alojamientos);
    }

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
