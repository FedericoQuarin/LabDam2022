package com.mdgz.dam.labdam2022.persistencia.room;

import android.content.Context;

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
import com.mdgz.dam.labdam2022.persistencia.room.mappers.UUIDConverter;
import com.mdgz.dam.labdam2022.persistencia.room.mappers.UbicacionMapper;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.CiudadRoomDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.roomDataSource.UbicacionRoomDataSource;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AlojamientoEntity.class, CiudadEntity.class, DepartamentoEntity.class, FavoritoEntity.class,
                      HabitacionEntity.class, HotelEntity.class, ReservaEntity.class, UbicacionEntity.class, UsuarioEntity.class},
          version = 9,
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
        return Room.databaseBuilder(context, LabDamDatabase.class, DATABASE_NAME)
                   .addCallback(new Callback() {
                       @Override
                       public void onCreate(@NonNull SupportSQLiteDatabase db) {
                           super.onCreate(db);
                           EXECUTOR_DB.execute(new Runnable() {
                               @Override
                               public void run() {

                                   // Cargar ciudades
                                   final OnResult<Ciudad> ciudadOnResult = new OnResult<Ciudad>() {
                                       @Override
                                       public void onSuccess(Ciudad result) {
                                           System.out.println(result.toString());
                                       }

                                       @Override
                                       public void onError(Throwable exception) {
                                           try {
                                               throw exception;
                                           } catch (Throwable e) {
                                               e.printStackTrace();
                                           }
                                       }
                                   };
                                   final CiudadRoomDataSource ciudadRoomDataSource = new CiudadRoomDataSource(instance);

                                   ciudadRoomDataSource.guardar(new Ciudad("Santa Fe", "SF"), ciudadOnResult);
                                   ciudadRoomDataSource.guardar(new Ciudad("Parana", "PN"), ciudadOnResult);
                                   ciudadRoomDataSource.guardar(new Ciudad("Rosario", "RS"), ciudadOnResult);

                                   // Cargar ubicacion
                                   final OnResult<Ubicacion> ubicacionOnResult = new OnResult<Ubicacion>() {
                                       @Override
                                       public void onSuccess(Ubicacion result) {
                                           System.out.println(result.toString());
                                       }

                                       @Override
                                       public void onError(Throwable exception) {
                                           try {
                                               throw exception;
                                           } catch (Throwable e) {
                                               e.printStackTrace();
                                           }
                                       }
                                   };

                                   final UbicacionRoomDataSource ubicacionRoomDataSource = new UbicacionRoomDataSource(instance);
                                   ubicacionRoomDataSource.guardar(new Ubicacion(
                                           50.0,
                                           50.0,
                                           "Lavaisse",
                                           "621",
                                           CiudadMapper.fromEntity(getInstance(context).ciudadDAO().getCiudades().get(0))),
                                           ubicacionOnResult);

                                   /*getInstance(context).ubicacionDAO().guardar(
                                           UbicacionMapper.toEntity(new Ubicacion(
                                                   50.0,
                                                   50.0,
                                                   "Lavaisse",
                                                   "621",
                                                   CiudadMapper.fromEntity(getInstance(context).ciudadDAO().getCiudades().get(0))
                                           ))
                                   );*/

                                   // Cartar departamento
                                   final OnResult<Departamento> departamentoOnResult = new OnResult<Departamento>() {
                                       @Override
                                       public void onSuccess(Departamento result) {

                                       }

                                       @Override
                                       public void onError(Throwable exception) {
                                           try {
                                               throw exception;
                                           } catch (Throwable e) {
                                               e.printStackTrace();
                                           }
                                       }
                                   };
                                   final AlojamientoRoomDataSource alojamientoRoomDataSource = new AlojamientoRoomDataSource(instance);



                                   MutableLiveData<Ubicacion> ubicacion1 = new MutableLiveData<Ubicacion>();
                                   ubicacionRoomDataSource.getUbicaciones(new OnResult<List<Ubicacion>>() {
                                       @Override
                                       public void onSuccess(List<Ubicacion> result) {
                                           ubicacion1.postValue(result.get(0));
                                       }

                                       @Override
                                       public void onError(Throwable exception) {

                                       }
                                   });

                                   UbicacionEntity ue = getInstance(context).ubicacionDAO().getUbicaciones().get(0);
                                   CiudadEntity ce = getInstance(context).ciudadDAO().getCiudadPorId(ue.getCiudadId().toString());

                                   alojamientoRoomDataSource.guardarDepartamento(new Departamento(
                                           "Depto1",
                                           "Un depto",
                                           3,
                                           100.0,
                                           true,
                                           50.0,
                                           2,
                                           UbicacionMapper.fromEntity(ue, ce),
                                           false), departamentoOnResult);
                                   /*getInstance(context).departamentoDAO().guardar(
                                           DepartamentoMapper.toEntity(new Departamento(
                                                   "Depto1",
                                                   "Un depto",
                                                   3,
                                                   100.0,
                                                   true,
                                                   50.0,
                                                   2,
                                                   UbicacionMapper.fromEntity(getInstance(context).ubicacionDAO().getUbicaciones().get(0)),
                                                   false
                                           ))
                                   );*/
                                   //System.out.println(UbicacionMapper.fromEntity(getInstance(context).ubicacionDAO().getUbicaciones().get(0)).toString());
                               }
                           });
                       }


                   })
                   .fallbackToDestructiveMigration()
                   .allowMainThreadQueries()
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
