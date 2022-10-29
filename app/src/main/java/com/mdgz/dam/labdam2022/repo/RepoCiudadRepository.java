package com.mdgz.dam.labdam2022.repo;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

public class RepoCiudadRepository {

    public static final List<Ciudad> _CIUDADES = List.of(
            new Ciudad(1,"Ciudad 1","ABC1"),
                new Ciudad(1,"Ciudad 2","ABC2"),
                new Ciudad(1,"Ciudad 3","ABC3"),
                new Ciudad(1,"Ciudad 4","ABC4")
        );

    public List<Ciudad> listaCiudades(){
        return  _CIUDADES;
    }
}
