package com.mdgz.dam.labdam2022.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.repositories.AlojamientoRepository;

import java.util.UUID;

public class DetalleAlojamientoViewModel extends ViewModel implements OnResult<Alojamiento> {
    final AlojamientoRepository alojamientoRepository;

    private MutableLiveData<Alojamiento> _alojamiento = new MutableLiveData<>();
    public LiveData<Alojamiento> alojamiento = _alojamiento;
    private MutableLiveData<Throwable> _error = new MutableLiveData<>();
    public LiveData<Throwable> error = _error;

    public DetalleAlojamientoViewModel(final AlojamientoRepository alojamientoRepository) {
        this.alojamientoRepository = alojamientoRepository;
    }

    public void buscarAlojamiento(UUID idAlojamiento) {
        new Thread(() -> {
            alojamientoRepository.recuperarAlojamiento(idAlojamiento, DetalleAlojamientoViewModel.this);
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
