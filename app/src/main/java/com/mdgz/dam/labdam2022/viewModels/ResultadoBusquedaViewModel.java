package com.mdgz.dam.labdam2022.viewModels;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.dataSources.OnResult;
import com.mdgz.dam.labdam2022.persistencia.repositories.AlojamientoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResultadoBusquedaViewModel extends ViewModel {
    final AlojamientoRepository alojamientoRepository;

    private UUID idUsuario;

    // El primer elemento de alojamientoCollection es la lista de alojamientos en si
    // El segundo marca la posicion actualizada en el caso de que se actualice una unica posicion
    private final MutableLiveData<Pair<List<Alojamiento>, Integer>> _alojamientoCollection
            = new MutableLiveData<>(new Pair<>(new ArrayList<>(), null));
    public LiveData<Pair<List<Alojamiento>, Integer>> alojamientoCollection = _alojamientoCollection;

    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>(false);
    public LiveData<Boolean> loading = _loading;
    private final MutableLiveData<Throwable> _error = new MutableLiveData<>(null);
    public LiveData<Throwable> error = _error;

    public ResultadoBusquedaViewModel(final AlojamientoRepository alojamientoRepository) {
        this.alojamientoRepository = alojamientoRepository;
        recuperarAlojamientos();
    }

    public void setearUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void recuperarAlojamientos() {
        new Thread(() -> {
            _loading.postValue(true);
            alojamientoRepository.recuperarAlojamientos(new OnResult<>() {
                @Override
                public void onSuccess(List<Alojamiento> result) {
                    _loading.postValue(false);
                    _alojamientoCollection.postValue(new Pair<>(result, null));
                }

                @Override
                public void onError(Throwable exception) {
                    _loading.postValue(false);
                    _error.postValue(exception);
                }
            });
        }).start();
    }

    public void cambiarFavorito(int posicion, boolean nuevoEstado) {
        if (alojamientoCollection.getValue() != null) {
            List<Alojamiento> alojamientos = alojamientoCollection.getValue().first;

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
                    alojamientoRepository.colocarFavorito(alojamientos.get(posicion).getId(), idUsuario, onResult);
                }
                else {
                    alojamientoRepository.quitarFavorito(alojamientos.get(posicion).getId(), idUsuario, onResult);
                }
            }).start();

            alojamientos.get(posicion).setEsFavorito(nuevoEstado);
            _alojamientoCollection.postValue(new Pair<>(alojamientos, posicion));
        }
    }
}
