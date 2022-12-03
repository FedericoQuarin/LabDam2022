package com.mdgz.dam.labdam2022.persistencia.room.roomDataSource;

import android.util.Pair;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.dataSources.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;

import java.util.LinkedHashSet;
import java.util.UUID;

public class FavoritoRoomDataSource implements FavoritoDataSource {

    @Override
    public void crearFavorito(UUID idAlojamiento, UUID idUsuario, OnResult<Favorito> callback) {

    }

    @Override
    public void recuperarFavoritos(UUID idUsuario, OnResult<LinkedHashSet<UUID>> callback) {

    }

    @Override
    public void eliminarFavorito(UUID idAlojamiento, OnResult<Favorito> callback) {

    }
}
