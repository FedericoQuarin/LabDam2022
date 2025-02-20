package com.mdgz.dam.labdam2022.exceptions;

import java.util.UUID;

// Excepcion que indica que se ha buscado una entidad por id y no fue hallada
public class EntidadNoEncontradaException extends RuntimeException{
    public EntidadNoEncontradaException(String clase, UUID idUtilizado) {
        super("Se ha buscado una entidad tipo " + clase + " con el id " + idUtilizado + " y no se ha encontrado");
    }
}
