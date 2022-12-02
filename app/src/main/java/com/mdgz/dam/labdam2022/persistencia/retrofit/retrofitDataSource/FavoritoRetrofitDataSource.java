package com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource;

import android.content.Context;
import android.util.Pair;

import com.mdgz.dam.labdam2022.exceptions.ProblemaConApiException;
import com.mdgz.dam.labdam2022.persistencia.dataSources.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.retrofit.RetrofitConfig;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.FavoritoEntity;
import com.mdgz.dam.labdam2022.persistencia.retrofit.mappers.FavoritoMapper;
import com.mdgz.dam.labdam2022.persistencia.retrofit.rest.FavoritoRest;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.AlojamientoRoomDataSource;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class FavoritoRetrofitDataSource implements FavoritoDataSource {
    private FavoritoRest favoritoRest;

    public FavoritoRetrofitDataSource() {
        RetrofitConfig retrofitConfig = RetrofitConfig.getInstance();

        favoritoRest = retrofitConfig.favoritoRest;
    }

    @Override
    public void crearFavorito(UUID idAlojamiento, UUID idUsuario, OnResult<Pair<UUID, UUID>> callback) {
        Call<FavoritoEntity> call = favoritoRest.guardarFavorito(FavoritoMapper.toEntity(idAlojamiento, idUsuario));

        try {
            Response<FavoritoEntity> response = call.execute();

            if (response.isSuccessful()) {
                callback.onSuccess(new Pair<>(idAlojamiento, idUsuario));
            }
            else {
                callback.onError(new ProblemaConApiException(response.code()));
            }
        }
        catch (IOException exc) {
            callback.onError(exc);
        }
    }

    @Override
    public void recuperarFavoritos(UUID idUsuario, OnResult<LinkedHashSet<UUID>> callback) {

    }

    @Override
    public void eliminarFavorito(UUID idAlojamiento, OnResult<Pair<UUID, UUID>> callback) {

    }
}
