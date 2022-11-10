package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Ciudad {

    private UUID id;
    private String nombre;
    private String abreviatura;

    public Ciudad(String nombre, String abreviatura) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public Ciudad(UUID id, String nombre, String abreviatura) {
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public UUID getId() {
        return id;
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

    @Override
    public String toString() {
        return "Ciudad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                '}';
    }
}
