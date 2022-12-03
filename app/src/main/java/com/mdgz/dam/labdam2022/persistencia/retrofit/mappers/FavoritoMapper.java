package com.mdgz.dam.labdam2022.persistencia.retrofit.mappers;

import android.util.Pair;

import androidx.annotation.NonNull;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.FavoritoEntity;

import java.util.UUID;

public class FavoritoMapper {
    public static Favorito toModelClass(@NonNull FavoritoEntity entity) {

        return new Favorito(entity.getAlojamientoId(), entity.getUsuarioId());
    }

    public static FavoritoEntity toEntity(@NonNull Favorito favorito) {
        return new FavoritoEntity(favorito.getIdAlojamiento(), favorito.getIdUsuario());
    }
}
