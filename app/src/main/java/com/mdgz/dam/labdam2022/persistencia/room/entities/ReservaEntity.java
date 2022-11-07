package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "reserva",
        foreignKeys = {@ForeignKey(entity = AlojamientoEntity.class, parentColumns = "id", childColumns = "alojamiento_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                       @ForeignKey(entity = UsuarioEntity.class, parentColumns = "id", childColumns = "usuario_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class ReservaEntity {

    @NonNull
    @PrimaryKey
    private UUID id;

    @ColumnInfo(name = "fecha_ingreso")
    private Long fechaIngreso;

    @ColumnInfo(name = "fecha_egreso")
    private Long fechaEgreso;

    private Boolean cancelada;

    @ColumnInfo(name = "cantidad_personas")
    private Integer cantidadPersonas;

    private Double monto;

    @ColumnInfo(name = "alojamiento_id")
    private UUID alojamientoId;

    @ColumnInfo(name = "usuario_id")
    private UUID usuarioID;

    public ReservaEntity(@NonNull UUID id, Long fechaIngreso, Long fechaEgreso, Boolean cancelada, Integer cantidadPersonas, Double monto, UUID alojamientoId, UUID usuarioID) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = cancelada;
        this.cantidadPersonas = cantidadPersonas;
        this.monto = monto;
        this.alojamientoId = alojamientoId;
        this.usuarioID = usuarioID;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public Long getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Long fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Long fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public void setAlojamientoId(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }
}
