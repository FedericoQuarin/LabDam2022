package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "departamento",
        foreignKeys = {@ForeignKey(entity = AlojamientoEntity.class, parentColumns = "id", childColumns = "alojamiento_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                       @ForeignKey(entity = UbicacionEntity.class, parentColumns = "id", childColumns = "ubicacion_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class DepartamentoEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private UUID idDepartamento;

    @ColumnInfo(name = "tiene_wifi")
    private Boolean tieneWifi;

    @ColumnInfo(name = "costo_limpieza")
    private Double costoLimpieza;

    @ColumnInfo(name = "cantidad_habitaciones")
    private Integer cantidadHabitaciones;

    @ColumnInfo(name = "alojamiento_id")
    private UUID alojamientoId;

    @ColumnInfo(name = "ubicacion_id")
    private UUID ubicacionId;

    public DepartamentoEntity(@NonNull final UUID id, final Boolean tieneWifi, final Double costoLimpieza,
                              final Integer cantidadHabitaciones, final UUID alojamientoId, final UUID ubicacionId) {
        this.idDepartamento = id;
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.alojamientoId = alojamientoId;
        this.ubicacionId = ubicacionId;
    }

    @NonNull
    public UUID getId() {
        return idDepartamento;
    }

    public void setId(@NonNull UUID id) {
        this.idDepartamento = id;
    }

    public Boolean getTieneWifi() {
        return tieneWifi;
    }

    public void setTieneWifi(Boolean tieneWifi) {
        this.tieneWifi = tieneWifi;
    }

    public Double getCostoLimpieza() {
        return costoLimpieza;
    }

    public void setCostoLimpieza(Double costoLimpieza) {
        this.costoLimpieza = costoLimpieza;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public void setAlojamientoId(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public UUID getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(UUID ubicacionId) {
        this.ubicacionId = ubicacionId;
    }
}
