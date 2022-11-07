package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Ubicacion {

    private UUID id;
    private Double lat;
    private Double lng;
    private String calle;
    private String numero;
    private Ciudad ciudad;

    public Ubicacion(){

    }

    public Ubicacion(Double lat, Double lng, String calle, String numero, Ciudad ciudad) {
        this.id = UUID.randomUUID();
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}
