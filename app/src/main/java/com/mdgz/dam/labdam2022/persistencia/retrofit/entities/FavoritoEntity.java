package com.mdgz.dam.labdam2022.persistencia.retrofit.entities;

import java.util.UUID;

public class FavoritoEntity {
    private UUID alojamientoId;
    private UUID usuarioId;

    public FavoritoEntity() {

    }

    public FavoritoEntity(UUID alojamientoId, UUID usuarioId) {
        this.alojamientoId = alojamientoId;
        this.usuarioId = usuarioId;
    }

    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public void setAlojamientoId(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }
}
