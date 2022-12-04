package com.mdgz.dam.labdam2022.persistencia.dataSources;

import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;
import java.util.UUID;

public interface ReservaDataSource {
    /*interface GuardarReservaCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarReservaCallback {
        void resultado(final boolean exito, final List<Reserva> resultados);
    }*/

    void guardarReserva(final Reserva reserva, final OnResult<Reserva> callback);
    void recuperarReservas(final OnResult<List<Reserva>> callback);
    void recuperarReserva(final UUID idReserva, final OnResult<Reserva> callback);
    void eliminarReserva(final UUID idReserva, final OnResult<Reserva> callback);
}
