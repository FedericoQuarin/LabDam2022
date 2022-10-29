package com.mdgz.dam.labdam2022.persistencia.interfaces;

import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.List;

public interface AlojamientoDataSource {
    interface GuardarAlojamientoCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarAlojamientoCallback {
        void resultado(final boolean exito, final List<Alojamiento> resultados);
    }
    void guardarAlojamiento(final Alojamiento entidad, final GuardarAlojamientoCallback callback);
    void recuperarAlojamiento(final RecuperarAlojamientoCallback callback);
}
