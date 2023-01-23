package com.mdgz.dam.labdam2022.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Usuario;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivityViewModel extends ViewModel {
    final private MutableLiveData<Alojamiento> _alojamientoSeleccionado = new MutableLiveData<>();
    final public LiveData<Alojamiento> alojamientoSeleccionado = _alojamientoSeleccionado;

    final private MutableLiveData<Usuario> _usuario = new MutableLiveData<>();
    final public LiveData<Usuario> usuario = _usuario;

    public MainActivityViewModel() {
        _usuario.setValue(new Usuario(UUID.fromString("34433635-b552-4b99-be2d-db8b820f0e21"),
                "Pedrito",
                "pedrito@gmail.com",
                new ArrayList<>(),
                new ArrayList<>()));
    }

    // Este metodo se utiliza para pasar el alojamiento seleccionado desde la pantalla
    // de resultadosBusqueda a la de detalle
    public void setearAlojamientoSeleccionado(Alojamiento alojamiento) {
        _alojamientoSeleccionado.postValue(alojamiento);
    }
}
