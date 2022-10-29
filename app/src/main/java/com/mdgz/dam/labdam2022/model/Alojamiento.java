package com.mdgz.dam.labdam2022.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ALOJAMIENTO")
public abstract class Alojamiento {

    @PrimaryKey
    protected Integer id;

    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;

    @ColumnInfo(name = "precio_base")
    protected Double precioBase;

    @ColumnInfo(name = "es_favorito")
    protected Boolean esFavorito;

    public abstract Ubicacion getUbicacion();
    public Double costoTotal(Long diasEstadia){
        return precioBase * diasEstadia;
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

    public void setId(Integer id) { this.id = id; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public void setPrecioBase(Double precioBase) { this.precioBase = precioBase; }

    public void setEsFavorito(Boolean esFavorito) { this.esFavorito = esFavorito; }

    public void turnFavorito() { this.esFavorito = !this.esFavorito; }
}
