package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;

@Dao
public interface ReservaDao {
    @Insert
    void save(Reserva... reserva);

    @Query("SELECT * FROM RESERVA")
    List<Reserva> getList();
}
