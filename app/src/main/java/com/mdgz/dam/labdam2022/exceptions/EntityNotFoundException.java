package com.mdgz.dam.labdam2022.exceptions;

// Excepcion que indica que se ha buscado una entidad por id y no fue hallada
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String clase, int idUtilizado) {
        super("Se ha buscado un entidad tipo " + clase + " con el id " + idUtilizado + " y no se ha encontrado");
    }
}
