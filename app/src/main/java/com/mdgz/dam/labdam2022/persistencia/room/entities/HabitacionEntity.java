package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "habitacion",
        indices = @Index(value = {"alojamiento_id", "hotel_id"}),
        foreignKeys = {@ForeignKey(entity = AlojamientoEntity.class, parentColumns = "id", childColumns = "alojamiento_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                       @ForeignKey(entity = HotelEntity.class, parentColumns = "id", childColumns = "hotel_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class HabitacionEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private UUID idHabitacion;

    @ColumnInfo(name = "camas_individuales")
    private Integer camasIndividuales;

    @ColumnInfo(name = "camas_matrimoniales")
    private Integer camasMatrimoniales;

    @ColumnInfo(name = "tiene_estacionamiento")
    private Boolean tieneEstacionamiento;

    @ColumnInfo(name = "alojamiento_id")
    private UUID alojamientoId;

    @ColumnInfo(name = "hotel_id")
    private UUID hotelId;

    public HabitacionEntity(@NonNull UUID idHabitacion, Integer camasIndividuales, Integer camasMatrimoniales, Boolean tieneEstacionamiento, UUID alojamientoId, UUID hotelId) {
        this.idHabitacion = idHabitacion;
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.alojamientoId = alojamientoId;
        this.hotelId = hotelId;
    }

    @NonNull
    public UUID getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(@NonNull UUID id) {
        this.idHabitacion = id;
    }

    public Integer getCamasIndividuales() {
        return camasIndividuales;
    }

    public void setCamasIndividuales(Integer camasIndividuales) {
        this.camasIndividuales = camasIndividuales;
    }

    public Integer getCamasMatrimoniales() {
        return camasMatrimoniales;
    }

    public void setCamasMatrimoniales(Integer camasMatrimoniales) {
        this.camasMatrimoniales = camasMatrimoniales;
    }

    public Boolean getTieneEstacionamiento() {
        return tieneEstacionamiento;
    }

    public void setTieneEstacionamiento(Boolean tieneEstacionamiento) {
        this.tieneEstacionamiento = tieneEstacionamiento;
    }

    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public void setAlojamientoId(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public void setHotelId(UUID hotelId) {
        this.hotelId = hotelId;
    }
}
