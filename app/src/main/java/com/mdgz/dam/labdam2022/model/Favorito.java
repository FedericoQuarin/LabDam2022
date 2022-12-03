package com.mdgz.dam.labdam2022.model;


import java.util.UUID;

public class Favorito {
    private UUID idAlojamiento;
    private UUID idUsuario;

    public Favorito() {

    }

    public Favorito(UUID idAlojamiento, UUID idUsuario) {
        this.idAlojamiento = idAlojamiento;
        this.idUsuario = idUsuario;
    }

    public UUID getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(UUID idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}
