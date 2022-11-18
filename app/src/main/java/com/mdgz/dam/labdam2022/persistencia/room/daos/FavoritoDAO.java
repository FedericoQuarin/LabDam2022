package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entities.FavoritoEntity;

import java.util.List;

@Dao
public interface FavoritoDAO {
    @Insert
    void save(FavoritoEntity... favoritos);

    @Delete
    void delete(FavoritoEntity favorito);

    @Query("SELECT * FROM FAVORITO")
    List<FavoritoEntity> getList();
}
