package com.mdgz.dam.labdam2022.persistencia.dataSources;

import com.mdgz.dam.labdam2022.persistencia.room.entities.FavoritoEntity;

import java.util.List;

public interface FavoritoDataSource {
    interface GuardarFavoritoCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarFavoritoCallback {
        void resultado(final boolean exito, final List<FavoritoEntity> resultados);
    }
    void guardarFavorito(final FavoritoEntity entidad, final FavoritoDataSource.GuardarFavoritoCallback callback);
    void recuperarFavorito(final FavoritoDataSource.RecuperarFavoritoCallback callback);
}
