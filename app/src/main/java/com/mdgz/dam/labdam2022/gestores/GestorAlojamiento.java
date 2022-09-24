package com.mdgz.dam.labdam2022.gestores;

import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.ArrayList;
import java.util.List;

public class GestorAlojamiento {

    private static GestorAlojamiento gestorAlojamiento;

    private List<Alojamiento> listaAlojamientos;

    private GestorAlojamiento(){

        listaAlojamientos = new ArrayList<Alojamiento>();
    }

    public static GestorAlojamiento getInstance(){

        if(gestorAlojamiento == null){

            gestorAlojamiento = new GestorAlojamiento();
        }

        return gestorAlojamiento;
    }

    public void agregarAlojamiento(Alojamiento a){

        this.listaAlojamientos.add(a);
    }

    public List<Alojamiento> getListaAlojamientos(){

        return this.listaAlojamientos;
    }
}
