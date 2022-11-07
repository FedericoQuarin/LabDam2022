package com.mdgz.dam.labdam2022.persistencia.room.dataSource;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.dataSources.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;

import java.util.List;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {

    /*
    METODOS QUE ESTABAN EN LA PRESENTACIÓN DEL LABORATORIO 4
    (DISTINTOS A LOS QUE IMPLEMENTÓ EL PROFE EN EL REPOSITORIO)

    @Override
    public void guardarAlojamiento(Alojamiento entidad, GuardarAlojamientoCallback callback) {

    }

    @Override
    public void recuperarAlojamiento(RecuperarAlojamientoCallback callback) {

    }
    */

    // METODOS UTILIZADOS POR LEANDRO

    @Override
    public void guardarHabitacion(Habitacion habitacion, OnResult<Habitacion> callback) {

    }

    @Override
    public void guardarDepartamento(Departamento departamento, OnResult<Departamento> callback) {

    }

    @Override
    public void recuperarHabitaciones(OnResult<List<Habitacion>> callback) {

    }

    @Override
    public void recuperarDepartamentos(OnResult<List<Departamento>> callback) {

    }

    @Override
    public void recuperarAlojamientos(OnResult<List<Alojamiento>> callback) {

    }


}
