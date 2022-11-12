package com.mdgz.dam.labdam2022.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.repositories.AlojamientoRepository;

import java.util.ArrayList;
import java.util.List;

public class ResultadoBusquedaViewModel extends ViewModel implements OnResult<List<Alojamiento>> {
    final AlojamientoRepository alojamientoRepository;

    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>(false);
    LiveData<Boolean> loading = _loading;
    private final MutableLiveData<List<Alojamiento>> _alojamientoCollection = new MutableLiveData<>(new ArrayList<>());
    LiveData<List<Alojamiento>> alojamientoCollection = _alojamientoCollection;
    private final MutableLiveData<Throwable> _error = new MutableLiveData<>(null);
    LiveData<Throwable> error = _error;

    public ResultadoBusquedaViewModel(final AlojamientoRepository alojamientoRepository) {
        this.alojamientoRepository = alojamientoRepository;
    }

    public void recuperarAlojamientos() {
        new Thread(() -> {
            _loading.postValue(true);
            alojamientoRepository.recuperarAlojamientos(ResultadoBusquedaViewModel.this);
        }).start();
    }

    @Override
    public void onSuccess(final List<Alojamiento> result) {
        _loading.postValue(false);
        _alojamientoCollection.postValue(result);
    }

    @Override
    public void onError(final Throwable exception) {
        _loading.postValue(false);
        _error.postValue(exception);
    }
}
