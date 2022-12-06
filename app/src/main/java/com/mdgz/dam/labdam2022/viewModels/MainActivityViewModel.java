package com.mdgz.dam.labdam2022.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.Alojamiento;

public class MainActivityViewModel extends ViewModel {
    final private MutableLiveData<Alojamiento> _alojamientoSeleccionado = new MutableLiveData<>();
    final public LiveData<Alojamiento> alojamientoSeleccionado = _alojamientoSeleccionado;

    public MainActivityViewModel() {
    }

    // Este metodo se utiliza para pasar el alojamiento seleccionado desde la pantalla
    // de resultadosBusqueda a la de detalle
    public void setearAlojamientoSeleccionado(Alojamiento alojamiento) {
        _alojamientoSeleccionado.postValue(alojamiento);
    }
}
