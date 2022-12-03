package com.mdgz.dam.labdam2022.persistencia.retrofit.retrofitDataSource;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.mdgz.dam.labdam2022.exceptions.ProblemaConApiException;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.dataSources.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.retrofit.RetrofitConfig;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.FavoritoEntity;
import com.mdgz.dam.labdam2022.persistencia.retrofit.mappers.FavoritoMapper;
import com.mdgz.dam.labdam2022.persistencia.retrofit.rest.FavoritoRest;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.AlojamientoRoomDataSource;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class FavoritoRetrofitDataSource implements FavoritoDataSource {
    final private FavoritoRest favoritoRest;

    public FavoritoRetrofitDataSource() {
        RetrofitConfig retrofitConfig = RetrofitConfig.getInstance();

        favoritoRest = retrofitConfig.favoritoRest;
    }

    @Override
    public void crearFavorito(UUID idAlojamiento, UUID idUsuario, OnResult<Favorito> callback) {
        Call<FavoritoEntity> call = favoritoRest.guardarFavorito(
                FavoritoMapper.toEntity(new Favorito(idAlojamiento, idUsuario)));

        try {
            Response<FavoritoEntity> response = call.execute();

            if (response.isSuccessful()) {
                Favorito favorito = FavoritoMapper.toModelClass(response.body());
                System.out.println("FavRetrofitDS - crearFavorito: " + favorito.getIdAlojamiento());
                callback.onSuccess(favorito);
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
        Call<List<FavoritoEntity>> call = favoritoRest.buscarFavoritos(idUsuario);

        try {
            Response<List<FavoritoEntity>> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                LinkedHashSet<UUID> hashSet = new LinkedHashSet<>();

                for (FavoritoEntity fav : response.body()) {
                    hashSet.add(fav.getAlojamientoId());
                }

                callback.onSuccess(hashSet);
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
    public void eliminarFavorito(UUID idAlojamiento, OnResult<Favorito> callback) {
        Call<ResponseBody> call = favoritoRest.borrarFavorito(idAlojamiento);

        try {
            Response<ResponseBody> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                callback.onSuccess(null);
            }
        }
        catch (IOException exc) {
            callback.onError(exc);
        }
    }
}
