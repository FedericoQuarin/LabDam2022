package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entities.DepartamentoEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface DepartamentoDAO {
    @Insert
    void guardar(DepartamentoEntity departamento);

    @Insert
    void guardar(List<DepartamentoEntity> departamentos);

    @Delete
    void borrar(DepartamentoEntity departamento);

    @Query("SELECT * FROM DEPARTAMENTO")
    List<DepartamentoEntity> getDepartamentos();

    @Query("SELECT * FROM DEPARTAMENTO where id=:departamentoId")
    DepartamentoEntity getDepartamentoPorId(UUID departamentoId);

    @Query("SELECT * FROM DEPARTAMENTO where alojamiento_id=:alojamientoId")
    DepartamentoEntity getDepartamentoPorIdAlojamiento(UUID alojamientoId);
}
