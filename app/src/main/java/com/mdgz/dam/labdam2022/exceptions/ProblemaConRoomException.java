package com.mdgz.dam.labdam2022.exceptions;

public class ProblemaConRoomException extends Exception {
    public ProblemaConRoomException() {
        super("Ha ocurrido un error intentando acceder a la BD");
    }
}
