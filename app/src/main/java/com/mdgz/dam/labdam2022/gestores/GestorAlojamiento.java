package com.mdgz.dam.labdam2022.gestores;

import com.mdgz.dam.labdam2022.exceptions.EntidadNoEncontradaException;
import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Clase encargada de gestionar los Alojamientos a nivel lógico
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

    public List<Alojamiento> getAlojamientos(){
        return this.listaAlojamientos;
    }

    public Alojamiento getAlojamiento(UUID idAlojamiento) {
        for(Alojamiento a : listaAlojamientos) {
            if (a.getId().equals(idAlojamiento)) return a;
        }

        throw new EntidadNoEncontradaException("Alojamiento", idAlojamiento);
    }
}
