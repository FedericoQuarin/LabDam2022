package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Favorito {

    private UUID id;

    UUID alojamientoID;
    UUID usuarioID;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
