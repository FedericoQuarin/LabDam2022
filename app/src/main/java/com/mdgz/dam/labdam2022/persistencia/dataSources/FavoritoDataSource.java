package com.mdgz.dam.labdam2022.persistencia.dataSources;

import android.util.Pair;

import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.LinkedHashSet;
import java.util.UUID;

public interface FavoritoDataSource {
    void crearFavorito(final UUID idAlojamiento, final UUID idUsuario, final OnResult<Favorito> callback);
    void recuperarFavoritos(final UUID idUsuario, final OnResult<LinkedHashSet<UUID>> callback);
    void eliminarFavorito(final UUID idAlojamiento, final OnResult<Favorito> callback);
}