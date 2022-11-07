package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.mdgz.dam.labdam2022.persistencia.DateTypeConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Reserva {

    private UUID id;
    private Date fechaIngreso;
    private Date fechaEgreso;
    private Boolean cancelada;
    private Integer cantidadPersonas;
    private Double monto;
    private UUID alojamientoID;
    private UUID usuarioID;

    public Reserva(Date fechaIngreso, Date fechaEgreso, Integer cantidadPersonas, Double monto, UUID alojamientoID) {
        this.id = UUID.randomUUID();
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = false;
        this.cantidadPersonas = cantidadPersonas;
        this.monto = monto;
        this.alojamientoID = alojamientoID;
    }

    public void cancelar(){
        this.cancelada = true;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public Date getFechaIngreso() { return fechaIngreso; }

    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public Date getFechaEgreso() { return fechaEgreso; }

    public void setFechaEgreso(Date fechaEgreso) { this.fechaEgreso = fechaEgreso; }

    public Boolean getCancelada() { return cancelada; }

    public void setCancelada(Boolean cancelada) { this.cancelada = cancelada; }

    public Integer getCantidadPersonas() { return cantidadPersonas; }

    public void setCantidadPersonas(Integer cantidadPersonas) {this.cantidadPersonas = cantidadPersonas; }

    public Double getMonto() { return monto; }

    public void setMonto(Double monto) { this.monto = monto; }

    public UUID getAlojamientoID() { return alojamientoID; }

    public void setAlojamientoID(UUID alojamientoID) { this.alojamientoID = alojamientoID; }

    public UUID getUsuarioID() { return usuarioID; }

    public void setUsuarioID(UUID usuarioID) { this.usuarioID = usuarioID; }
}
