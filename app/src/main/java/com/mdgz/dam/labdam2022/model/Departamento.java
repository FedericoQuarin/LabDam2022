package com.mdgz.dam.labdam2022.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class Departamento extends Alojamiento{

    @ColumnInfo(name = "tiene_wifi")
    private Boolean tieneWifi;
    @ColumnInfo(name = "costo_limpieza")
    private Double costoLimpieza;
    @ColumnInfo(name = "cantidad_habitaciones")
    private Integer cantidadHabitaciones;
    @Ignore
    private Ubicacion ubicacion;

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Departamento(){
        super();
    }

    public Departamento(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones,Ubicacion ubicacion, Boolean esFavorito) {
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
}
