package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Hotel {

    private UUID id;
    private String nombre;
    private Integer categoria;
    private Ubicacion ubicacion;

    public Hotel(){
        super();
    }

    public Hotel(String nombre, Integer categoria, Ubicacion ubicacion) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
