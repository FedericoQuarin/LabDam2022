package com.mdgz.dam.labdam2022.viewModels;

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
import java.util.UUID;

public class DetalleAlojamientoViewModel extends ViewModel implements OnResult<Alojamiento> {
    final AlojamientoRepository alojamientoRepository;
    final ReservaRepository reservaRepository;

    private MutableLiveData<Alojamiento> _alojamiento = new MutableLiveData<>();
    public LiveData<Alojamiento> alojamiento = _alojamiento;
    private MutableLiveData<Throwable> _error = new MutableLiveData<>();
    public LiveData<Throwable> error = _error;
    private MutableLiveData<Boolean> _reservaExitosa = new MutableLiveData<>();
    public LiveData<Boolean> reservaExitosa = _reservaExitosa;

    public DetalleAlojamientoViewModel(final AlojamientoRepository alojamientoRepository,
                                       final ReservaRepository reservaRepository) {
        this.alojamientoRepository = alojamientoRepository;
        this.reservaRepository = reservaRepository;
    }

    public void buscarAlojamiento(UUID idAlojamiento) {
        new Thread(() -> {
            alojamientoRepository.recuperarAlojamiento(idAlojamiento, DetalleAlojamientoViewModel.this);
        }).start();
    }

    public void crearReserva(Date fechaIngreso,
                             Date fechaEgreso,
                             Integer cantidadPersonas,
                             Double monto,
                             Alojamiento alojamiento,
                             Usuario usuario) {
        Reserva reserva = new Reserva(fechaIngreso, fechaEgreso, cantidadPersonas, monto, alojamiento, usuario);
        new Thread(() -> {
            reservaRepository.guardarReserva(reserva, new OnResult<>() {
                @Override
                public void onSuccess(Reserva result) {
                    _reservaExitosa.postValue(true);
                }

                @Override
                public void onError(Throwable exception) {
                    _error.postValue(exception);
                }
            });
        }).start();
    }

    @Override
    public void onSuccess(Alojamiento result) {
        _alojamiento.postValue(result);
    }

    @Override
    public void onError(Throwable exception) {
        _error.postValue(exception);
    }
}
