package com.mdgz.dam.labdam2022.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.repositories.CiudadRepository;

import java.util.ArrayList;
import java.util.List;

public class BusquedaViewModel extends ViewModel implements OnResult<List<Ciudad>> {
    private final MutableLiveData<List<Ciudad>> _ciudadCollection = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<Ciudad>> ciudadCollection = _ciudadCollection;
    private final MutableLiveData<Throwable> _error = new MutableLiveData<>(null);
    public LiveData<Throwable> error = _error;

    private CiudadRepository ciudadRepository;
    public BusquedaViewModel(final CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
        recuperarCiudades();
    }

    public void recuperarCiudades() {
        new Thread(() -> {
            ciudadRepository.getCiudades(BusquedaViewModel.this);
        }).start();
    }

    @Override
    public void onSuccess(List<Ciudad> result) {
        _ciudadCollection.postValue(result);
    }

    @Override
    public void onError(final Throwable exception) {
        _error.postValue(exception);
    }
}
