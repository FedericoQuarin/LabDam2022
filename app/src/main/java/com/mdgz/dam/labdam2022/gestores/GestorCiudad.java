package com.mdgz.dam.labdam2022.gestores;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.factory.CiudadRepositoryFactory;
import com.mdgz.dam.labdam2022.persistencia.repositories.CiudadRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Clase encargada de gestionar las ciudades a nivel lógico
public class GestorCiudad {
    private static GestorCiudad gestorCiudad;

    private CiudadRepository ciudadRepository;

    private GestorCiudad(Context ctx){
        ciudadRepository = CiudadRepositoryFactory.create(ctx);
        /*ciudadRep = CiudadRepository.getInstance(ctx.getApplicationContext());
        listaCiudades = ciudadRep.getList();*/
    }

    public static GestorCiudad getInstance(Context ctx){
        if(gestorCiudad == null){
            gestorCiudad = new GestorCiudad(ctx);
        }

        return gestorCiudad;
    }

    /*public void agregarCiudad(Ciudad ciudad) {
        ciudadRepository.guardar(ciudad);
    }*/

    public List<Ciudad> getCiudades() {
        // Para no llevar la logica del onResult a la interfaz se implementan aca
        final List<Ciudad> listaCiudades = new ArrayList<Ciudad>();

        ciudadRepository.getCiudades(new OnResult<List<Ciudad>>() {
            @Override
            public void onSuccess(List<Ciudad> result) {
                listaCiudades.addAll(result);
            }

            @Override
            public void onError(Throwable exception) {
                // TODO: ver que pasa si no encuentra
                System.out.println("Hubo un error encontrando ciudades");
            }
        });

        return listaCiudades;
    }

    public List<String> getNombresCiudades() {
        final List<Ciudad> listaCiudades = new ArrayList<Ciudad>();

        ciudadRepository.getCiudades(new OnResult<List<Ciudad>>() {
            @Override
            public void onSuccess(List<Ciudad> result) {
                listaCiudades.addAll(result);
            }

            @Override
            public void onError(Throwable exception) {
                // TODO: ver que pasa si no encuentra
                System.out.println("Hubo un error encontrando ciudades");
            }
        });

        return listaCiudades.stream()
                .map(c -> c.getNombre())
                .collect(Collectors.toList());
    }
    /*
    public Ciudad getCiudad(Integer id) {
        return ciudadRep.getCiudad(id);
    }

    public static List<Ciudad> ciudadesIniciales() {
        List<Ciudad> ciudadesIniciales = new ArrayList<>();
        ciudadesIniciales.add(new Ciudad("Santa Fe", "SF"));
        ciudadesIniciales.add(new Ciudad("Paraná", "PA"));
        ciudadesIniciales.add(new Ciudad("Rosario", "RS"));

        return ciudadesIniciales;
    }*/
}
