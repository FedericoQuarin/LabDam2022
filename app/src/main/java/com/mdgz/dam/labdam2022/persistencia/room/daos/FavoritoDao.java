package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;

@Dao
public interface FavoritoDao {
    @Insert
    void save(Favorito... favoritos);

    @Delete
    void delete(Favorito favorito);

    @Query("SELECT * FROM FAVORITO")
    List<Favorito> getList();
}
