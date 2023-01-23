package com.mdgz.dam.labdam2022.viewModels;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.repositories.AlojamientoRepository;
import com.mdgz.dam.labdam2022.persistencia.repositories.ReservaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DetalleAlojamientoViewModel extends ViewModel {
    final AlojamientoRepository alojamientoRepository;
    final ReservaRepository reservaRepository;

    private Usuario usuario;

    final private MutableLiveData<Alojamiento> _alojamiento = new MutableLiveData<>();
    final public LiveData<Alojamiento> alojamiento = _alojamiento;
    final private MutableLiveData<Boolean> _reservaExitosa = new MutableLiveData<>();
    final public LiveData<Boolean> reservaExitosa = _reservaExitosa;
    final private MutableLiveData<Throwable> _errorReservar = new MutableLiveData<>();
    final public LiveData<Throwable> errorReservar = _errorReservar;
    final private MutableLiveData<Throwable> _error = new MutableLiveData<>();
    final public LiveData<Throwable> error = _error;


    public DetalleAlojamientoViewModel(final AlojamientoRepository alojamientoRepository,
                                       final ReservaRepository reservaRepository) {
        this.alojamientoRepository = alojamientoRepository;
        this.reservaRepository = reservaRepository;
    }

    public void setearUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setearAlojamiento(Alojamiento alojamiento) {
        _alojamiento.setValue(alojamiento);
    }

    public void crearReserva(Date fechaIngreso,
                             Date fechaEgreso,
                             Integer cantidadPersonas,
                             Double monto,
                             Alojamiento alojamiento) {
        Reserva reserva = new Reserva(fechaIngreso, fechaEgreso, cantidadPersonas, monto, alojamiento, usuario);
        new Thread(() -> {
            reservaRepository.guardarReserva(reserva, new OnResult<>() {
                @Override
                public void onSuccess(Reserva result) {
                    _reservaExitosa.postValue(true);
                }

                @Override
                public void onError(Throwable exception) {
                    _errorReservar.postValue(exception);
                }
            });
        }).start();
    }

    // Se utiliza para avisar al viewModel de que el error fue observado
    public void observadoErrorReservar() {
        _errorReservar.postValue(null);
    }

    public void cambiarFavorito(boolean nuevoEstado) {
        if (alojamiento.getValue() != null) {
            Alojamiento aloj = alojamiento.getValue();

            new Thread(() -> {
                OnResult<UUID> onResult = new OnResult<>() {
                    @Override
                    public void onSuccess(UUID result) {

                    }

                    @Override
                    public void onError(Throwable exception) {
                        _error.postValue(exception);
                    }
                };

                if (nuevoEstado) {
                    alojamientoRepository.colocarFavorito(aloj.getId(), usuario.getId(), onResult);
                }
                else {
                    alojamientoRepository.quitarFavorito(aloj.getId(), usuario.getId(), onResult);
                }
            }).start();

            aloj.setEsFavorito(nuevoEstado);
            _alojamiento.postValue(aloj);
        }
    }
}
