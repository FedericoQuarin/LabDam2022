package com.mdgz.dam.labdam2022.model;

import java.util.List;
import java.util.UUID;

public class Usuario {

    private UUID id;
    private String nombre;
    private String email;

    private List<Reserva> listaReservas;
    private List<Alojamiento> listaFavoritos;

    public Usuario(String nombre, String email, List<Reserva> listaReservas, List<Alojamiento> listaFavoritos) {
        this.nombre = nombre;
        this.email = email;
        this.listaReservas = listaReservas;
        this.listaFavoritos = listaFavoritos;
    }

    public Usuario(UUID id, String nombre, String email, List<Reserva> listaReservas, List<Alojamiento> listaFavoritos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.listaReservas = listaReservas;
        this.listaFavoritos = listaFavoritos;
    }

    public UUID getId() { return id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<Reserva> getListaReservas() { return listaReservas; }

    public void setListaReservas(List<Reserva> listaReservas) { this.listaReservas = listaReservas; }

    public List<Alojamiento> getListaFavoritos() { return listaFavoritos; }

    public void setListaFavoritos(List<Alojamiento> listaFavoritos) { this.listaFavoritos = listaFavoritos; }
}
