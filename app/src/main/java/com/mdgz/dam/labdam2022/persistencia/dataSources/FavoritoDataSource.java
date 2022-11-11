package com.mdgz.dam.labdam2022.persistencia.dataSources;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.room.entities.FavoritoEntity;

import java.util.List;

public interface FavoritoDataSource {
    interface GuardarFavoritoCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarFavoritoCallback {
        void resultado(final boolean exito, final List<Alojamiento> resultados);
    }
    void guardarFavorito(final FavoritoEntity entidad, final FavoritoDataSource.GuardarFavoritoCallback callback);
    void recuperarFavorito(final FavoritoDataSource.RecuperarFavoritoCallback callback);
}

/*class FavoritoRoomDataSource implements FavoritoDataSource {
    final AlojamientoDataSource alojamientoDS;

    FavoritoRoomDataSource(AlojamientoDataSource alojamientoDS) {
        this.alojamientoDS = alojamientoDS;
    }

    @Override
    public void guardarFavorito(FavoritoEntity entidad, GuardarFavoritoCallback callback) {

    }

    @Override
    public void recuperarFavorito(RecuperarFavoritoCallback callback) {
        // me traigo los favoritosentity
        alojamientoDS.recuperarAlojamientos(new OnResult<List<Alojamiento>>() {
            @Override
            public void onSuccess(List<Alojamiento> result) {
                //filter
                callback.resultado();
            }

            @Override
            public void onError(Throwable exception) {

            }
        });
    }

}*/