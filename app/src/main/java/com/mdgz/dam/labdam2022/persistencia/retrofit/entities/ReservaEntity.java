package com.mdgz.dam.labdam2022.persistencia.retrofit.entities;

import java.util.Date;
import java.util.UUID;

public class ReservaEntity {
    private UUID alojamientoId;
    private UUID usuarioId;
    private Date fechaIngreso;
    private Date fechaSalida;

    public ReservaEntity() {

    }

    public ReservaEntity(UUID alojamientoId, UUID usuarioId, Date fechaIngreso, Date fechaSalida) {
        this.alojamientoId = alojamientoId;
        this.usuarioId = usuarioId;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
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