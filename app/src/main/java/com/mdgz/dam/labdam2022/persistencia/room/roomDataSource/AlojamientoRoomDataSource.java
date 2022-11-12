package com.mdgz.dam.labdam2022.persistencia.room.roomDataSource;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.dataSources.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.room.LabDamDatabase;
import com.mdgz.dam.labdam2022.persistencia.room.daos.AlojamientoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UbicacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.AlojamientoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.DepartamentoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.HabitacionMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    private final AlojamientoDAO alojamientoDAO;
    private final HabitacionDAO habitacionDAO;
    private final DepartamentoDAO departamentoDAO;
    private final UbicacionDAO ubicacionDAO;
    private final CiudadDAO ciudadDAO;
    private final HotelDAO hotelDAO;

    public AlojamientoRoomDataSource(final Context context) {
        this(LabDamDatabase.getInstance(context));
    }

    public AlojamientoRoomDataSource(final LabDamDatabase db) {
        alojamientoDAO = db.alojamientoDAO();
        habitacionDAO = db.habitacionDAO();
        departamentoDAO = db.departamentoDAO();
        ubicacionDAO = db.ubicacionDAO();
        ciudadDAO = db.ciudadDAO();
        hotelDAO = db.hotelDAO();
    }


    // METODOS UTILIZADOS POR LEANDRO

    @Override
    public void guardarHabitacion(Habitacion habitacion, OnResult<Habitacion> callback) {
        try {

            final AlojamientoEntity alojamientoEntity = AlojamientoMapper.toEntity(habitacion);
            alojamientoDAO.guardar(alojamientoEntity);

            final HabitacionEntity habitacionEntity = HabitacionMapper.toEntity(habitacion);
            habitacionDAO.guardar(habitacionEntity);

            callback.onSuccess(habitacion);

        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void guardarDepartamento(Departamento departamento, OnResult<Departamento> callback) {
        try {

            final AlojamientoEntity alojamientoEntity = AlojamientoMapper.toEntity(departamento);
            alojamientoDAO.guardar(alojamientoEntity);

            final DepartamentoEntity departamentoEntity = DepartamentoMapper.toEntity(departamento);
            departamentoDAO.guardar(departamentoEntity);

            callback.onSuccess(departamento);

        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarHabitaciones(OnResult<List<Habitacion>> callback) {
        try {
            final List<HabitacionEntity> listaHabitacionEntities = habitacionDAO.getHabitaciones();
            final List<Habitacion> listaHabitaciones = new ArrayList<>();

            AlojamientoEntity alojamientoEntity;
            HotelEntity hotelEntity;
            UbicacionEntity ubicacionEntity;
            CiudadEntity ciudadEntity;
            for (final HabitacionEntity habitacionEntity : listaHabitacionEntities) {
                alojamientoEntity = alojamientoDAO.getAlojamientoPorId(habitacionEntity.getAlojamientoId());
                hotelEntity = hotelDAO.getHotelPorId(habitacionEntity.getHotelId().toString());
                ubicacionEntity = ubicacionDAO.getUbicacionPorId(hotelEntity.getUbicacionId().toString());
                ciudadEntity = ciudadDAO.getCiudadPorId(ubicacionEntity.getCiudadId().toString());

                listaHabitaciones.add(HabitacionMapper.fromEntity(habitacionEntity, alojamientoEntity, hotelEntity, ubicacionEntity, ciudadEntity));
            }
            callback.onSuccess(listaHabitaciones);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarDepartamentos(OnResult<List<Departamento>> callback) {
        try {
            final List<DepartamentoEntity> listaDepartamentoEntities = departamentoDAO.getDepartamentos();
            final List<Departamento> listaDepartamentos = new ArrayList<>();

            AlojamientoEntity alojamientoEntity;
            for (final DepartamentoEntity DepartamentoEntity : listaDepartamentoEntities) {

                alojamientoEntity = alojamientoDAO.getAlojamientoPorId(DepartamentoEntity.getAlojamientoId());

                //listaDepartamentos.add(DepartamentoMapper.fromEntity(DepartamentoEntity, alojamientoEntity));
            }
            callback.onSuccess(listaDepartamentos);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarAlojamientos(OnResult<List<Alojamiento>> callback) {
        try {
            final List<AlojamientoEntity> listaAlojamientoEntities = alojamientoDAO.getAlojamientos();
            final List<Alojamiento> listaAlojamientos = new ArrayList<>();

            DepartamentoEntity departamentoEntity;
            HabitacionEntity habitacionEntity;
            UbicacionEntity ubicacionEntity;
            CiudadEntity ciudadEntity;
            HotelEntity hotelEntity;

            for (final AlojamientoEntity alojamientoEntity : listaAlojamientoEntities) {
                // Si es un alojamiento
                if (Objects.equals(alojamientoEntity.getTipo(), AlojamientoEntity.TIPO_DEPARTAMENTO)) {

                    departamentoEntity = departamentoDAO.getDepartamentoPorIdAlojamiento(alojamientoEntity.getId());
                    ubicacionEntity = ubicacionDAO.getUbicacionPorId(departamentoEntity.getUbicacionId().toString());
                    ciudadEntity = ciudadDAO.getCiudadPorId(ubicacionEntity.getCiudadId().toString());
                    listaAlojamientos.add(DepartamentoMapper.fromEntity(departamentoEntity, alojamientoEntity, ubicacionEntity, ciudadEntity));
                }
                // Si es una habitacion
                else {
                    habitacionEntity = habitacionDAO.getHabitacionPorIdAlojamiento(alojamientoEntity.getId());
                    hotelEntity = hotelDAO.getHotelPorId(habitacionEntity.getHotelId().toString());
                    ubicacionEntity = ubicacionDAO.getUbicacionPorId(hotelEntity.getUbicacionId().toString());
                    ciudadEntity = ciudadDAO.getCiudadPorId(ubicacionEntity.getCiudadId().toString());

                    listaAlojamientos.add(HabitacionMapper.fromEntity(habitacionEntity, alojamientoEntity, hotelEntity, ubicacionEntity, ciudadEntity));

                }
            }
            callback.onSuccess(listaAlojamientos);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarAlojamiento(UUID idAlojamiento, OnResult<Alojamiento> callback) {
        try {
            final DepartamentoEntity deptoEntity = departamentoDAO.getDepartamentoPorId(idAlojamiento);

            AlojamientoEntity alojamientoEntity = alojamientoDAO.getAlojamientoPorId(idAlojamiento);
            UbicacionEntity ubicacionEntity = ubicacionDAO.getUbicacionPorId(deptoEntity.getUbicacionId().toString());
            CiudadEntity ciudadEntity = ciudadDAO.getCiudadPorId(ubicacionEntity.getCiudadId().toString());
            Departamento depto = DepartamentoMapper.fromEntity(deptoEntity, alojamientoEntity, ubicacionEntity, ciudadEntity);

            callback.onSuccess(depto);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }


}
