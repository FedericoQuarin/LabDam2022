package com.mdgz.dam.labdam2022.gestores;

import com.mdgz.dam.labdam2022.model.Reserva;

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

    public void agregarReserva(Reserva r){
        this.listaReservas.add(r);
    }

    public List<Reserva> getReservas(){
        return this.listaReservas;
    }

}
