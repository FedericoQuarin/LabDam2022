package com.mdgz.dam.labdam2022.persistencia.retrofit.rest;

import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.FavoritoEntity;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FavoritoRest {
    @POST("favorito")
    Call<FavoritoEntity> guardarFavorito(@Body FavoritoEntity favorito);

    @GET("favorito")
    Call<List<FavoritoEntity>> buscarFavoritos(@Query("usuarioId") UUID idUsuario);

    @DELETE("favorito")
    Call<ResponseBody> borrarFavorito(@Query("alojamientoId") UUID idAlojamiento);
}
