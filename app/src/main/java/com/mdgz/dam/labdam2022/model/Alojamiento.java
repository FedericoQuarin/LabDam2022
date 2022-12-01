package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public abstract class Alojamiento {

    protected UUID id;
    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;
    protected Double precioBase;
    protected Boolean esFavorito;

    public abstract Ubicacion getUbicacion();
    public Double costoTotal(Long diasEstadia){
        return precioBase * diasEstadia;
    }

    public Alojamiento() {
        this.id = UUID.randomUUID();
    }

    public Alojamiento(String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean esFavorito) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.esFavorito = esFavorito;
    }

    public Alojamiento(UUID id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean esFavorito) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.esFavorito = esFavorito;
    }

    public UUID getId(){
        return this.id;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public Integer getCapacidad(){
        return this.capacidad;
    }

    public Double getPrecioBase(){
        return this.precioBase;
    }

    public Boolean getEsFavorito() { return esFavorito; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public void setPrecioBase(Double precioBase) { this.precioBase = precioBase; }

    public void setEsFavorito(Boolean esFavorito) { this.esFavorito = esFavorito; }

    public void turnFavorito() { this.esFavorito = !this.esFavorito; }
}
