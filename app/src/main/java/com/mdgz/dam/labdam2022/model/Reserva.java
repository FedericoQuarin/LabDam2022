package com.mdgz.dam.labdam2022.model;

import java.util.Date;
import java.util.UUID;

public class Reserva {

    private UUID id;
    private Date fechaIngreso;
    private Date fechaEgreso;
    private Boolean cancelada;
    private Integer cantidadPersonas;
    private Double monto;
    private Alojamiento alojamiento;
    private Usuario usuario;

    public Reserva(Date fechaIngreso, Date fechaEgreso, Integer cantidadPersonas, Double monto, Alojamiento alojamiento, Usuario usuario) {
        this.id = UUID.randomUUID();
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = false;
        this.cantidadPersonas = cantidadPersonas;
        this.monto = monto;
        this.alojamiento = alojamiento;
        this.usuario = usuario;
    }

    public Reserva(UUID id, Date fechaIngreso, Date fechaEgreso, Integer cantidadPersonas, Double monto, Alojamiento alojamiento, Usuario usuario) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = false;
        this.cantidadPersonas = cantidadPersonas;
        this.monto = monto;
        this.alojamiento = alojamiento;
        this.usuario = usuario;
    }

    public void cancelar(){
        this.cancelada = true;
    }

    public UUID getId() { return id; }

    public Date getFechaIngreso() { return fechaIngreso; }

    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public Date getFechaEgreso() { return fechaEgreso; }

    public void setFechaEgreso(Date fechaEgreso) { this.fechaEgreso = fechaEgreso; }

    public Boolean getCancelada() { return cancelada; }

    public void setCancelada(Boolean cancelada) { this.cancelada = cancelada; }

    public Integer getCantidadPersonas() { return cantidadPersonas; }

    public void setCantidadPersonas(Integer cantidadPersonas) {this.cantidadPersonas = cantidadPersonas; }

    public Double getMonto() { return monto; }

    public void setMonto(Double monto) { this.monto = monto; }

    public Alojamiento getAlojamiento() { return alojamiento; }

    public void setAlojamiento(Alojamiento alojamiento) { this.alojamiento = alojamiento; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
