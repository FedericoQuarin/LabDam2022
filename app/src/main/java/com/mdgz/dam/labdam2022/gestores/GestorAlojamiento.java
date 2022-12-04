package com.mdgz.dam.labdam2022.gestores;

import android.content.Context;

import com.mdgz.dam.labdam2022.exceptions.EntidadNoEncontradaException;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.factories.AlojamientoRepositoryFactory;
import com.mdgz.dam.labdam2022.persistencia.repositories.AlojamientoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Clase encargada de gestionar los Alojamientos a nivel lógico
@Deprecated
public class GestorAlojamiento {

    // Gestores
    private static GestorAlojamiento gestorAlojamiento;

    // Repositorios
    private AlojamientoRepository alojamientoRepository;

    private List<Alojamiento> listaAlojamientos;

    private GestorAlojamiento(Context ctx){
        listaAlojamientos = new ArrayList<Alojamiento>();

        alojamientoRepository = AlojamientoRepositoryFactory.create(ctx);
    }

    public static GestorAlojamiento getInstance(Context ctx){
        if(gestorAlojamiento == null){
            gestorAlojamiento = new GestorAlojamiento(ctx);
        }

        return gestorAlojamiento;
    }

    public void agregarAlojamiento(Alojamiento a){
        this.listaAlojamientos.add(a);
    }

    public List<Alojamiento> getAlojamientos(){

        List<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();

        alojamientoRepository.recuperarAlojamientos(null, new OnResult<List<Alojamiento>>() {
            @Override
            public void onSuccess(List<Alojamiento> result) {
                listaAlojamientos.addAll(result);
            }

            @Override
            public void onError(Throwable exception) {
                try {
                    throw exception;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        return listaAlojamientos;
    }

    public Alojamiento getAlojamiento(UUID idAlojamiento) {

        List<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();

        alojamientoRepository.recuperarAlojamientos(null, new OnResult<List<Alojamiento>>() {
            @Override
            public void onSuccess(List<Alojamiento> result) {
                listaAlojamientos.addAll(result);
            }

            @Override
            public void onError(Throwable exception) {
                try {
                    throw exception;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        for(Alojamiento a : listaAlojamientos) {
            if (a.getId().equals(idAlojamiento)) return a;
        }

        throw new EntidadNoEncontradaException("Alojamiento", idAlojamiento);
    }
}
