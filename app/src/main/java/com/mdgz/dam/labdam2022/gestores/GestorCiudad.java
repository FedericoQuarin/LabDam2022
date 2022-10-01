package com.mdgz.dam.labdam2022.gestores;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.ArrayList;
import java.util.List;

// Clase encargada de gestionar las ciudades a nivel lógico
public class GestorCiudad {
    private static GestorCiudad gestorCiudad;

    private List<Ciudad> listaCiudades;

    private GestorCiudad(){
        listaCiudades = new ArrayList<Ciudad>();
    }

    public static GestorCiudad getInstance(){
        if(gestorCiudad == null){
            gestorCiudad = new GestorCiudad();
        }

        return gestorCiudad;
    }

    public void agregarCiudad(Ciudad ciudad) {
        listaCiudades.add(ciudad);
    }

    public List<Ciudad> getCiudades() {
        return listaCiudades;
    }
}
