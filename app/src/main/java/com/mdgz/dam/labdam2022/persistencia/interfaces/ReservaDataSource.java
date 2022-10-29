package com.mdgz.dam.labdam2022.persistencia.interfaces;

import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;

public interface ReservaDataSource {
    interface GuardarReservaCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarReservaCallback {
        void resultado(final boolean exito, final List<Reserva> resultados);
    }
    void guardarReserva(final Reserva entidad, final ReservaDataSource.GuardarReservaCallback callback);
    void recuperarReserva(final ReservaDataSource.RecuperarReservaCallback callback);
}
