package com.mdgz.dam.labdam2022.persistencia.dataSources;

import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;

public interface FavoritoDataSource {
    interface GuardarFavoritoCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarFavoritoCallback {
        void resultado(final boolean exito, final List<Favorito> resultados);
    }
    void guardarFavorito(final Favorito entidad, final FavoritoDataSource.GuardarFavoritoCallback callback);
    void recuperarFavorito(final FavoritoDataSource.RecuperarFavoritoCallback callback);
}
