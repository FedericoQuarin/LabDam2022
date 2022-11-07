package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Habitacion  extends Alojamiento {

    private UUID idHabitacion;
    private Integer camasIndividuales;
    private Integer camasMatrimoniales;
    private Boolean tieneEstacionamiento;
    private Hotel hotel;

    public Habitacion(String titulo, String descripcion, Integer capacidad, Double precioBase, Integer camasIndividuales, Integer camasMatrimoniales, Boolean tieneEstacionamiento, Hotel hotel, Boolean esFavorito) {
        super(titulo, descripcion, capacidad, precioBase, esFavorito);
        this.idHabitacion = UUID.randomUUID();
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotel = hotel;
    }

    public UUID getIdHabitacion() { return idHabitacion; }

    public int getCamasIndividuales() {
        return camasIndividuales;
    }

    public void setCamasIndividuales(int camasIndividuales) { this.camasIndividuales = camasIndividuales; }

    public int getCamasMatrimoniales() {
        return camasMatrimoniales;
    }

    public void setCamasMatrimoniales(int camasMatrimoniales) { this.camasMatrimoniales = camasMatrimoniales; }

    public Boolean getTieneEstacionamiento() {
        return tieneEstacionamiento;
    }

    public void setTieneEstacionamiento(Boolean tieneEstacionamiento) { this.tieneEstacionamiento = tieneEstacionamiento; }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public Ubicacion getUbicacion() {
        return hotel.getUbicacion();
    }

}
