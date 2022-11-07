package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entities.UsuarioEntity;

import java.util.List;

@Dao
public interface UsuarioDAO {
    @Insert
    void guardar(UsuarioEntity usuario);

    @Delete
    void borrar(UsuarioEntity usuario);

    @Query("SELECT * FROM USUARIO")
    List<UsuarioEntity> getUsuarios();
}
