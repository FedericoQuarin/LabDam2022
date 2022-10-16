package com.mdgz.dam.labdam2022.model;

import java.time.Instant;

public class Reserva {

    private static Integer numeroID = 0;

    private Integer id;
    private Instant fechaIngreso;
    private Instant fechaEgreso;
    private Boolean cancelada;
    private Integer cantidadPersonas;
    private Double monto;
    private Alojamiento alojamiento;

    public Reserva(Instant fechaIngreso, Instant fechaEgreso, Integer cantidadPersonas, Double monto, Alojamiento alojamiento) {
        this.id = numeroID;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = false;
        this.cantidadPersonas = cantidadPersonas;
        this.monto = monto;
        this.alojamiento = alojamiento;
        numeroID++;
    }

    public void cancelar(){
        this.cancelada = true;
    }
}
