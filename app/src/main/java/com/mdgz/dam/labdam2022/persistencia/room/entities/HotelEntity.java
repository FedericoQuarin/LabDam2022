package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.UUID;

@Entity(tableName = "hotel",
        foreignKeys = {@ForeignKey(entity = UbicacionEntity.class, parentColumns = "id", childColumns = "ubicacion_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),})
public class HotelEntity {

    @NonNull
    @PrimaryKey
    UUID id;

    String nombre;

    Integer categoria;

    @ColumnInfo(name = "ubicacion_id")
    UUID ubicacionId;

    public HotelEntity(@NonNull UUID id, String nombre, Integer categoria, UUID ubicacionId) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacionId = ubicacionId;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public UUID getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(UUID ubicacionId) {
        this.ubicacionId = ubicacionId;
    }
}
