package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entities.ReservaEntity;

import java.util.List;

@Dao
public interface ReservaDAO {
    @Insert
    void guardar(ReservaEntity reserva);

    @Insert
    void guardar(List<ReservaEntity> reservas);

    @Delete
    void borrar(ReservaEntity reserva);

    @Query("SELECT * FROM RESERVA")
    List<ReservaEntity> getReservas();
}
