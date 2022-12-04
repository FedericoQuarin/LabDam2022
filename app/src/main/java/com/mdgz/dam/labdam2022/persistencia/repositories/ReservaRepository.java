package com.mdgz.dam.labdam2022.persistencia.repositories;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.dataSources.ReservaDataSource;

import java.util.List;
import java.util.UUID;

public class ReservaRepository implements ReservaDataSource {
    private ReservaDataSource ds;

    public ReservaRepository(ReservaDataSource ds) {
        this.ds = ds;
    }
    @Override
    public void guardarReserva(Reserva reserva, OnResult<Reserva> callback) {
        ds.guardarReserva(reserva, callback);
    }

    @Override
    public void recuperarReservas(OnResult<List<Reserva>> callback) {
        ds.recuperarReservas(callback);
    }

    @Override
    public void recuperarReserva(UUID idReserva, OnResult<Reserva> callback) {
        recuperarReserva(idReserva, callback);
    }

    @Override
    public void eliminarReserva(UUID idReserva, OnResult<Reserva> callback) {
        ds.eliminarReserva(idReserva, callback);
    }
}
