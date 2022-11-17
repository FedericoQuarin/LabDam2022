package com.mdgz.dam.labdam2022.persistencia.retrofit.entities;

import java.util.Date;
import java.util.UUID;

public class ReservaEntity {
    private UUID alojamientoID;
    private UUID usuarioID;
    private Date fechaIngreso;
    private Date fechaSalida;

    public ReservaEntity() {

    }

    public ReservaEntity(UUID alojamientoID, UUID usuarioID, Date fechaIngreso, Date fechaSalida) {
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public UUID getAlojamientoID() {
        return alojamientoID;
    }

    public void setAlojamientoID(UUID alojamientoID) {
        this.alojamientoID = alojamientoID;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}