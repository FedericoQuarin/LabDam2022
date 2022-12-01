package com.mdgz.dam.labdam2022.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.repositories.ReservaRepository;

import java.util.ArrayList;
import java.util.List;

public class MisReservasViewModel extends ViewModel {
    private ReservaRepository reservaRepository;

    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>(false);
    public LiveData<Boolean> loading = _loading;
    private final MutableLiveData<List<Reserva>> _listaReservas = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<Reserva>> listaReservas = _listaReservas;
    private final MutableLiveData<Throwable> _error = new MutableLiveData<>(null);
    public LiveData<Throwable> error = _error;

    public MisReservasViewModel(final ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
        recuperarReservas();
    }

    public void recuperarReservas() {
        new Thread(() -> {
            _loading.postValue(true);
            reservaRepository.recuperarReservas(new OnResult<>() {
                @Override
                public void onSuccess(List<Reserva> result) {
                    _loading.postValue(false);
                    _listaReservas.postValue(result);
                }

                @Override
                public void onError(Throwable exception) {
                    _loading.postValue(false);
                    _error.postValue(exception);
                }
            });
        }).start();
    }
}
