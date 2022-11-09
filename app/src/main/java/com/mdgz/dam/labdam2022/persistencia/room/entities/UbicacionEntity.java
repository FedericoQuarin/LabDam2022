package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.UUID;

@Entity(tableName = "ubicacion",
        indices = @Index(value = {"ciudad_id"}),
        foreignKeys = {@ForeignKey(entity = CiudadEntity.class, parentColumns = "id", childColumns = "ciudad_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),})
public class UbicacionEntity {

    @NonNull
    @PrimaryKey
    private UUID id;

    private Double lat;

    private Double lng;

    private String calle;

    private String numero;

    @ColumnInfo(name = "ciudad_id")
    private UUID ciudadId;

    public UbicacionEntity(@NonNull UUID id, Double lat, Double lng, String calle, String numero, UUID ciudadId) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudadId = ciudadId;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public UUID getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(UUID ciudadId) {
        this.ciudadId = ciudadId;
    }
}
