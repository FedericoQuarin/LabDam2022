package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Departamento extends Alojamiento{

    private UUID departamentoId;
    private Boolean tieneWifi;
    private Double costoLimpieza;
    private Integer cantidadHabitaciones;
    private Ubicacion ubicacion;

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Departamento(String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones,Ubicacion ubicacion, Boolean esFavorito) {
        super(titulo, descripcion, capacidad, precioBase, esFavorito);
        this.departamentoId = UUID.randomUUID();
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacion = ubicacion;
    }

    public UUID getDeptoId() { return departamentoId; }

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
}
