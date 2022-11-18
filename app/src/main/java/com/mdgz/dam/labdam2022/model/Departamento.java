package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Departamento extends Alojamiento{

    private Boolean tieneWifi;
    private Double costoLimpieza;
    private Integer cantidadHabitaciones;
    private Ubicacion ubicacion;

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Departamento(String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones,Ubicacion ubicacion, Boolean esFavorito) {
        super(titulo, descripcion, capacidad, precioBase, esFavorito);
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacion = ubicacion;
    }

    public Departamento(UUID id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones,Ubicacion ubicacion, Boolean esFavorito) {
        super(id, titulo, descripcion, capacidad, precioBase, esFavorito);
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacion = ubicacion;
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

    @Override
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    @Override
    public Double costoTotal(Long diasEstadia){
        return precioBase * diasEstadia + costoLimpieza;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad=" + capacidad +
                ", precioBase=" + precioBase +
                ", esFavorito=" + esFavorito +
                ", tieneWifi=" + tieneWifi +
                ", costoLimpieza=" + costoLimpieza +
                ", cantidadHabitaciones=" + cantidadHabitaciones +
                ", ubicacion=" + ubicacion +
                '}';
    }
}
