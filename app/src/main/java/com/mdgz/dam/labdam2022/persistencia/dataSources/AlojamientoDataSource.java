package com.mdgz.dam.labdam2022.persistencia.dataSources;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;

import java.util.List;
import java.util.UUID;

public interface AlojamientoDataSource {

    /*
    METODOS QUE ESTABAN EN LA PRESENTACIÓN DEL LABORATORIO 4
    (DISTINTOS A LOS QUE IMPLEMENTÓ EL PROFE EN EL REPOSITORIO)

    interface GuardarAlojamientoCallback { void resultado(final boolean exito); }
    interface RecuperarAlojamientoCallback { void resultado(final boolean exito, final List<Alojamiento> resultados); }

    void guardarAlojamiento(final Alojamiento entidad, final GuardarAlojamientoCallback callback);

    void recuperarAlojamiento(final RecuperarAlojamientoCallback callback);
    */

    // METODOS UTILIZADOS POR LEANDRO

    void guardarHabitacion(Habitacion habitacion, OnResult<Habitacion> callback);

    void guardarDepartamento(Departamento departamento, OnResult<Departamento> callback);

    void recuperarHabitaciones(OnResult<List<Habitacion>> callback);

    void recuperarDepartamentos(OnResult<List<Departamento>> callback);

    void recuperarAlojamientos(OnResult<List<Alojamiento>> callback);

    void recuperarAlojamiento(final UUID idAlojamiento, OnResult<Alojamiento> callback);
}
