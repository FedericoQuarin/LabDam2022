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
    public void recuperarReserva(UUID id, OnResult<Reserva> callback) {
        recuperarReserva(id, callback);
    }

    @Override
    public void eliminarReserva(UUID id, OnResult<Reserva> callback) {
        ds.eliminarReserva(id, callback);
    }
}
