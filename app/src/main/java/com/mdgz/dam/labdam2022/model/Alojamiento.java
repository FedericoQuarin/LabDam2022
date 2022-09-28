package com.mdgz.dam.labdam2022.model;

public abstract class Alojamiento {

    protected Integer id;
    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;
    protected Double precioBase;
    protected Boolean esFavorito;

    public abstract Ubicacion getUbicacion();
    public Double costoDia(){
        return precioBase;
    }

    public Alojamiento(){
        super();
    }

    public Alojamiento(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean esFavorito) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.esFavorito = esFavorito;
    }

    public Integer getId(){
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

    public void turnFavorito() { this.esFavorito = !this.esFavorito; }
}
