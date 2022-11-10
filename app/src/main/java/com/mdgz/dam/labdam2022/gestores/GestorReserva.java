package com.mdgz.dam.labdam2022.gestores;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GestorReserva {

    private static GestorReserva gestorReserva;

    private List<Reserva> listaReservas;

    private GestorReserva(){
        listaReservas = new ArrayList<Reserva>();
    }

    public static GestorReserva getInstance(){
        if(gestorReserva == null){
            gestorReserva = new GestorReserva();
        }

        return gestorReserva;
    }

    public void crearReserva(Date fechaIngreso, Date fechaEgreso, Integer cantidadPersonas, Double monto, Alojamiento alojamiento, Usuario usuario) {
        agregarReserva(new Reserva(fechaIngreso, fechaEgreso, cantidadPersonas, monto, alojamiento, usuario));
    }

    private void agregarReserva(Reserva r){
        this.listaReservas.add(r);
    }

    public List<Reserva> getReservas(){
        return this.listaReservas;
    }

}
