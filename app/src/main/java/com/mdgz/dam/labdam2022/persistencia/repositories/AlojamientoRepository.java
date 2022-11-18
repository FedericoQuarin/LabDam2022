package com.mdgz.dam.labdam2022.persistencia.repositories;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.dataSources.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;

import java.util.List;
import java.util.UUID;

// Aca decimos que el repository implementa el ds para de forma
// facil nos exponga todos los metodos pero no es necesario y podría
// no ser lo que buscamos ( ej: exponer menos metodos o componer metodos del o los datasource)
public class AlojamientoRepository implements AlojamientoDataSource {

    private final AlojamientoDataSource alojamientoDataSource;

    public AlojamientoRepository(final AlojamientoDataSource ds) { this.alojamientoDataSource = ds; }

    @Override
    public void guardarHabitacion(Habitacion habitacion, OnResult<Habitacion> callback) {
        alojamientoDataSource.guardarHabitacion(habitacion, callback);
    }

    @Override
    public void guardarDepartamento(Departamento departamento, OnResult<Departamento> callback) {
        alojamientoDataSource.guardarDepartamento(departamento, callback);
    }

    @Override
    public void recuperarHabitaciones(OnResult<List<Habitacion>> callback) {
        alojamientoDataSource.recuperarHabitaciones(callback);
    }

    @Override
    public void recuperarDepartamentos(OnResult<List<Departamento>> callback) {
        alojamientoDataSource.recuperarDepartamentos(callback);
    }

    @Override
    public void recuperarAlojamientos(OnResult<List<Alojamiento>> callback) {
        alojamientoDataSource.recuperarAlojamientos(callback);
    }

    @Override
    public void recuperarAlojamiento(UUID idAlojamiento, OnResult<Alojamiento> callback) {
        alojamientoDataSource.recuperarAlojamiento(idAlojamiento, callback);
    }
}
