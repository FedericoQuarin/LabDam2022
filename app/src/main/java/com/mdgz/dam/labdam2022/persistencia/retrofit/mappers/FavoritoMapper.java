package com.mdgz.dam.labdam2022.persistencia.retrofit.mappers;

import android.util.Pair;

import androidx.annotation.NonNull;

import com.mdgz.dam.labdam2022.persistencia.retrofit.entities.FavoritoEntity;

import java.util.UUID;

public class FavoritoMapper {
    public static Pair<UUID, UUID> toModelClass(@NonNull FavoritoEntity entity) {

        return new Pair<>(entity.getAlojamientoId(), entity.getUsuarioId());
    }

    public static FavoritoEntity toEntity(@NonNull UUID idAlojamiento, UUID idUsuario) {
        return new FavoritoEntity(idAlojamiento, idUsuario);
    }
}
