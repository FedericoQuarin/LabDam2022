package com.mdgz.dam.labdam2022.gestores;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.room.CiudadRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Clase encargada de gestionar las ciudades a nivel lógico
public class GestorCiudad {
    private static GestorCiudad gestorCiudad;

    private List<Ciudad> listaCiudades;

    private CiudadRepository ciudadRep;

    private GestorCiudad(Context ctx){
        ciudadRep = CiudadRepository.getInstance(ctx.getApplicationContext());
        listaCiudades = ciudadRep.getList();
    }

    public static GestorCiudad getInstance(Context ctx){
        if(gestorCiudad == null){
            gestorCiudad = new GestorCiudad(ctx);
        }

        return gestorCiudad;
    }

    public void agregarCiudad(Ciudad ciudad) {
        ciudadRep.save(ciudad);
    }

    public List<Ciudad> getCiudades() {
        return listaCiudades;
    }

    public List<String> getNombresCiudades() {
        return listaCiudades.stream()
                .map(c -> c.getNombre())
                .collect(Collectors.toList());
    }
}
