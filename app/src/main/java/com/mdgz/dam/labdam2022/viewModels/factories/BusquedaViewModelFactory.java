package com.mdgz.dam.labdam2022.viewModels.factories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mdgz.dam.labdam2022.persistencia.factory.CiudadRepositoryFactory;
import com.mdgz.dam.labdam2022.persistencia.repositories.CiudadRepository;
import com.mdgz.dam.labdam2022.viewModels.BusquedaViewModel;

public class BusquedaViewModelFactory implements ViewModelProvider.Factory{
    private final Context context;

    public BusquedaViewModelFactory(final Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BusquedaViewModel.class)) {
            final CiudadRepository repo = CiudadRepositoryFactory.create(context);
            //noinspection unchecked
            return (T) new BusquedaViewModel(repo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
