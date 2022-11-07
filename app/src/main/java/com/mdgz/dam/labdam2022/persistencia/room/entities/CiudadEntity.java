package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "ciudad")
public class CiudadEntity {

    @NonNull
    @PrimaryKey
    private UUID id;

    private String nombre;

    private String abreviatura;

    public CiudadEntity(@NonNull UUID id, String nombre, String abreviatura) {
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
}
