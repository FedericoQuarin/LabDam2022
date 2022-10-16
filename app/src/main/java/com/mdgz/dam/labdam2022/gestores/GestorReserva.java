package com.mdgz.dam.labdam2022.gestores;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    public void crearReserva(Instant fechaIngreso, Instant fechaEgreso, Integer cantidadPersonas, Double monto, Alojamiento alojamiento) {
        agregarReserva(new Reserva(fechaIngreso, fechaEgreso, cantidadPersonas, monto, alojamiento));
    }

    private void agregarReserva(Reserva r){
        this.listaReservas.add(r);
    }

    public List<Reserva> getReservas(){
        return this.listaReservas;
    }

}
