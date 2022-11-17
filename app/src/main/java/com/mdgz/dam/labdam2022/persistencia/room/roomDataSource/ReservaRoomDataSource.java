package com.mdgz.dam.labdam2022.persistencia.room.roomDataSource;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.dataSources.ReservaDataSource;

import java.util.List;
import java.util.UUID;

public class ReservaRoomDataSource implements ReservaDataSource {
    @Override
    public void guardarReserva(Reserva reserva, OnResult<Reserva> callback) {

    }

    @Override
    public void recuperarReservas(OnResult<List<Reserva>> callback) {

    }

    @Override
    public void recuperarReserva(UUID id, OnResult<List<Reserva>> callback) {

    }
}
