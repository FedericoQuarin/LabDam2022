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

public class MisFavoritosViewModel extends ViewModel {
    private AlojamientoRepository alojamientoRepository;

    private UUID idUsuario;

    private Integer posicion;

    final private MutableLiveData<List<Alojamiento>> _alojamientos = new MutableLiveData<>(new ArrayList<>());
    final public LiveData<List<Alojamiento>> alojamientos = _alojamientos;
    final private MutableLiveData<Boolean> _loading = new MutableLiveData<>(false);
    final public LiveData<Boolean> loading = _loading;
    final private MutableLiveData<Throwable> _error = new MutableLiveData<>(null);
    final public LiveData<Throwable> error = _error;

    public MisFavoritosViewModel(final AlojamientoRepository alojamientoRepository) {
        this.alojamientoRepository = alojamientoRepository;
    }

    public void setearUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getPosicion() {
        return this.posicion;
    }

    public void buscarFavoritos() {
        new Thread(() -> {
            _loading.postValue(true);
            alojamientoRepository.recuperarAlojamientosFavoritos(idUsuario, new OnResult<List<Alojamiento>>() {
                @Override
                public void onSuccess(List<Alojamiento> result) {
                    _loading.postValue(false);
                    _alojamientos.postValue(result);
                }

                @Override
                public void onError(Throwable exception) {
                    _loading.postValue(false);
                    _error.postValue(exception);
                }
            });
        }).start();
    }

    public void borrarFavorito(int posicion) {
        if (alojamientos.getValue() != null) {
            List<Alojamiento> listaAlojamientos = alojamientos.getValue();
            UUID id = listaAlojamientos.get(posicion).getId();

            new Thread(() -> {
                OnResult<UUID> onResult = new OnResult<>() {
                    @Override
                    public void onSuccess(UUID result) {
                        // noop
                    }

                    @Override
                    public void onError(Throwable exception) {
                        _error.postValue(exception);
                    }
                };

                alojamientoRepository.quitarFavorito(id, idUsuario, onResult);
            }).start();

            listaAlojamientos.remove(posicion);
            this.posicion = posicion;
            _alojamientos.postValue(listaAlojamientos);
        }
    }
}
