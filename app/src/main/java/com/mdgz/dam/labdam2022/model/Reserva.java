package com.mdgz.dam.labdam2022.model;

import java.time.Instant;

public class Reserva {

    private Integer id;
    private Instant fechaIngreso;
    private Instant fechaEgreso;
    private Boolean cancelada;
    private Double monto;
    private Alojamiento alojamiento;

    public Reserva(Integer id, Instant fechaIngreso, Instant fechaEgreso, Double monto, Alojamiento alojamiento) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = false;
        this.monto = monto;
        this.alojamiento = alojamiento;
    }

    public void cancelar(){
        this.cancelada = true;
    }
}
