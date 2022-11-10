package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entities.HotelEntity;

import java.util.List;

@Dao
public interface HotelDAO {
    @Insert
    void guardar(HotelEntity hotel);

    @Insert
    void guardar(List<HotelEntity> hoteles);

    @Delete
    void borrar(HotelEntity reserva);

    @Query("SELECT * FROM HOTEL")
    List<HotelEntity> getHoteles();

    @Query("SELECT * FROM HOTEL WHERE id = :id")
    HotelEntity getHotelPorId(String id);
}
