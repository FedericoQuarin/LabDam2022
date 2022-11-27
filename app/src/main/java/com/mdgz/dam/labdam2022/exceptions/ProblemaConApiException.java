package com.mdgz.dam.labdam2022.exceptions;

public class ProblemaConApiException extends Exception {
    private int codigoError;

    public ProblemaConApiException(int codigoError) {
        super("Hubo un problema al acceder a la API remota. Codigo: " + codigoError);
        this.codigoError = codigoError;
    }

    public int getCodigoError() {
        return codigoError;
    }
}
