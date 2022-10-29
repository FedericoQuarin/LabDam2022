package com.mdgz.dam.labdam2022.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Reserva {

    private static Integer numeroID = 0;

    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "fecha_ingreso")
    private Instant fechaIngreso;

    @ColumnInfo(name = "fecha_egreso")
    private Instant fechaEgreso;

    private Boolean cancelada;

    @ColumnInfo(name = "cantidad_personas")
    private Integer cantidadPersonas;

    private Double monto;
    private Alojamiento alojamiento;
    private UUID alojamientoID;
    private UUID usuarioID;

    public Reserva(Instant fechaIngreso, Instant fechaEgreso, Integer cantidadPersonas, Double monto, Alojamiento alojamiento) {
        this.id = numeroID;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = false;
        this.cantidadPersonas = cantidadPersonas;
        this.monto = monto;
        this.alojamiento = alojamiento;
        numeroID++;
    }

    public void cancelar(){
        this.cancelada = true;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Instant getFechaIngreso() { return fechaIngreso; }

    public void setFechaIngreso(Instant fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public Instant getFechaEgreso() { return fechaEgreso; }

    public void setFechaEgreso(Instant fechaEgreso) { this.fechaEgreso = fechaEgreso; }

    public Boolean getCancelada() { return cancelada; }

    public void setCancelada(Boolean cancelada) { this.cancelada = cancelada; }

    public Integer getCantidadPersonas() { return cantidadPersonas; }

    public void setCantidadPersonas(Integer cantidadPersonas) {this.cantidadPersonas = cantidadPersonas; }

    public Double getMonto() { return monto; }

    public void setMonto(Double monto) { this.monto = monto; }

    public Alojamiento getAlojamiento() { return alojamiento; }

    public void setAlojamiento(Alojamiento alojamiento) { this.alojamiento = alojamiento; }

    public UUID getAlojamientoID() { return alojamientoID; }

    public void setAlojamientoID(UUID alojamientoID) { this.alojamientoID = alojamientoID; }

    public UUID getUsuarioID() { return usuarioID; }

    public void setUsuarioID(UUID usuarioID) { this.usuarioID = usuarioID; }
}
