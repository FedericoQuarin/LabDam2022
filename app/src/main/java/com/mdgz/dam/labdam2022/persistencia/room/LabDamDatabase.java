package com.mdgz.dam.labdam2022.persistencia.room;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.room.daos.AlojamientoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.FavoritoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.ReservaDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDAO;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UsuarioDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.FavoritoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.ReservaEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UbicacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UsuarioEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.AlojamientoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.CiudadMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.DepartamentoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.HotelMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.UUIDConverter;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.UbicacionMapper;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.CiudadRoomDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.HotelRoomDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.UbicacionRoomDataSource;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AlojamientoEntity.class, CiudadEntity.class, DepartamentoEntity.class, FavoritoEntity.class,
                      HabitacionEntity.class, HotelEntity.class, ReservaEntity.class, UbicacionEntity.class, UsuarioEntity.class},
          version = 10,
          exportSchema = false)
@TypeConverters({UUIDConverter.class})
public abstract class LabDamDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "labdam_db";
    private static LabDamDatabase instance;

    public abstract AlojamientoDAO alojamientoDAO();
    public abstract CiudadDAO ciudadDAO();
    public abstract DepartamentoDAO departamentoDAO();
    public abstract FavoritoDAO favoritoDAO();
    public abstract HabitacionDAO habitacionDAO();
    public abstract HotelDAO hotelDAO();
    public abstract ReservaDAO reservaDAO();
    public abstract UbicacionDAO ubicacionDAO();
    public abstract UsuarioDAO usuarioDAO();

    public static final ExecutorService EXECUTOR_DB = Executors.newSingleThreadExecutor();

    public synchronized static LabDamDatabase getInstance(Context context){
        if (instance == null) {
            instance = buildDatabase(context);
        }
        return instance;
    }

    private static LabDamDatabase buildDatabase(final Context context) {
        Runnable runnable = () -> {
            // Se obtienen roomDataSources necesarios
            final CiudadRoomDataSource ciudadRoomDataSource = new CiudadRoomDataSource(instance);
            final UbicacionRoomDataSource ubicacionRoomDataSource = new UbicacionRoomDataSource(instance);
            final AlojamientoRoomDataSource alojamientoRoomDataSource = new AlojamientoRoomDataSource(instance);
            final HotelRoomDataSource hotelRoomDataSource = new HotelRoomDataSource(instance);

            // Se crean los onResult necesarios para utilizar los metodos
            final OnResult<Ciudad> ciudadOnResult = new OnResult<>() {
                @Override
                public void onSuccess(Ciudad result) {
                    // noop
                }

                @Override
                public void onError(Throwable exception) {
                    // noop
                }
            };

            final OnResult<Ubicacion> ubicacionOnResult = new OnResult<>() {
                @Override
                public void onSuccess(Ubicacion result) {
                    // noop
                }

                @Override
                public void onError(Throwable exception) {
                    // noop
                }
            };

            final OnResult<Departamento> departamentoOnResult = new OnResult<>() {
                @Override
                public void onSuccess(Departamento result) {
                    // noop
                }

                @Override
                public void onError(Throwable exception) {
                    // noop
                }
            };

            final OnResult<Hotel> hotelOnResult = new OnResult<>() {
                @Override
                public void onSuccess(Hotel result) {
                    // noop
                }

                @Override
                public void onError(Throwable exception) {
                    // noop
                }
            };

            final OnResult<Habitacion> habitacionOnResult = new OnResult<Habitacion>() {
                @Override
                public void onSuccess(Habitacion result) {
                    // noop
                }

                @Override
                public void onError(Throwable exception) {
                    // noop
                }
            };

            // Se crean los objetos iniciales
            final Ciudad santaFe = new Ciudad("Santa Fe", "SF");
            final Ciudad parana = new Ciudad("Parana", "PN");
            final Ciudad rosario = new Ciudad("Rosario", "RS");

            final Ubicacion ubicacion1 = new Ubicacion(
                    50.0,
                    50.0,
                    "Lavaisse",
                    "610",
                    santaFe);
            final Ubicacion ubicacion2 = new Ubicacion(
                    30.0,
                    30.0,
                    "Balcarce",
                    "1442",
                    rosario);

            final Departamento depto1 = new Departamento(
                    UUID.fromString("77969e64-7291-4a32-b648-ca0364007e83"),
                    "Depto1",
                    "Un depto",
                    3,
                    100.0,
                    true,
                    50.0,
                    2,
                    ubicacion1,
                    false);
            final Departamento depto2 = new Departamento(
                    UUID.fromString("4adc5326-2c5b-4440-a472-d146998deced"),
                    "Depto2",
                    "Un depto",
                    4,
                    300.0,
                    true,
                    50.0,
                    2,
                    ubicacion1,
                    false);
            final Departamento depto3 = new Departamento(
                    UUID.fromString("36028e3f-3dc7-4b85-9d27-bbb2bc6702a9"),
                    "Depto3",
                    "Un depto",
                    6,
                    1000.0,
                    false,
                    50.0,
                    3,
                    ubicacion1,
                    false);

            final Hotel hotel = new Hotel(
                    "Hotel principal",
                    1,
                    ubicacion2);

            final Habitacion habitacion1 = new Habitacion(
                    UUID.fromString("c884754c-c1c8-4d19-ab7f-615e41e64f20"),
                    "Habitacion1",
                    "Descripcion",
                    3,
                    100.0,
                    1,
                    1,
                    true,
                    hotel,
                    false);

            final Habitacion habitacion2 = new Habitacion(
                    UUID.fromString("8c480cdf-6462-4e58-a059-cd6d73d0f13a"),
                    "Habitacion2",
                    "Descripcion",
                    2,
                    100.0,
                    0,
                    1,
                    true,
                    hotel,
                    false);
            final Habitacion habitacion3 = new Habitacion(
                    UUID.fromString("aac90105-4c7e-4c08-9e0a-bce3de6eb3c5"),
                    "Habitacion3",
                    "Descripcion",
                    2,
                    100.0,
                    0,
                    1,
                    true,
                    hotel,
                    false);

            // Se guardar los objetos creados
            ciudadRoomDataSource.guardar(santaFe, ciudadOnResult);
            ciudadRoomDataSource.guardar(parana, ciudadOnResult);
            ciudadRoomDataSource.guardar(rosario, ciudadOnResult);

            ubicacionRoomDataSource.guardar(ubicacion1, ubicacionOnResult);
            ubicacionRoomDataSource.guardar(ubicacion2, ubicacionOnResult);

            alojamientoRoomDataSource.guardarDepartamento(depto1, departamentoOnResult);
            alojamientoRoomDataSource.guardarDepartamento(depto2, departamentoOnResult);
            alojamientoRoomDataSource.guardarDepartamento(depto3, departamentoOnResult);

            hotelRoomDataSource.guardar(hotel, hotelOnResult);

            alojamientoRoomDataSource.guardarHabitacion(habitacion1, habitacionOnResult);
            alojamientoRoomDataSource.guardarHabitacion(habitacion2, habitacionOnResult);
            alojamientoRoomDataSource.guardarHabitacion(habitacion3, habitacionOnResult);

            Log.e("F", "f");
        };
        return Room.databaseBuilder(context, LabDamDatabase.class, DATABASE_NAME)
                   .addCallback(new Callback() {
                       @Override
                       public void onCreate(@NonNull SupportSQLiteDatabase db) {
                           super.onCreate(db);
                           EXECUTOR_DB.execute(runnable);
                       }

                       @Override
                       public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                           super.onDestructiveMigration(db);
                           EXECUTOR_DB.execute(runnable);
                       }
                   })
                   .fallbackToDestructiveMigration()
                   //.allowMainThreadQueries()
                   .build();
    }



    /*private static final RoomDatabase.Callback mRoomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull final SupportSQLiteDatabase db) {
            super.onCreate(db);
            EXECUTOR_DB.execute(new Runnable() {
                @Override
                public void run() {
                    getInstance()
                }
            });
            /*EXECUTOR_DB.execute(() -> {
                /*final OnResult<Departamento> dc = new OnResult<Departamento>() {

                    @Override
                    public void onSuccess(final Departamento result) {
                        // noop
                    }

                    @Override
                    public void onError(final Throwable exception) {
                        // noop
                    }
                };
                final OnResult<Habitacion> hc = new OnResult<Habitacion>() {

                    @Override
                    public void onSuccess(final Habitacion result) {
                        // noop
                    }

                    @Override
                    public void onError(final Throwable exception) {
                        // noop
                    }
                };

                final CiudadRoomDataSource ciudadRoomDataSource = new CiudadRoomDataSource(instance);
                ciudadRoomDataSource.guardar(new Ciudad("Santa Fe","SF"), ciudadRoomDataSource);
            });
        }
    };


    private static LabDamDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, LabDamDatabase.class, "labdam_db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        //getInstance(context).ciudadDao().save(GestorCiudad.ciudadesIniciales());
                                    }
                                }
                        );
                    }
                })
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }*/
}
