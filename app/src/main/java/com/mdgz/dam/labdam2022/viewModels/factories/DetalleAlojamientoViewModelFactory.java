package com.mdgz.dam.labdam2022.viewModels.factories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mdgz.dam.labdam2022.persistencia.factories.AlojamientoRepositoryFactory;
import com.mdgz.dam.labdam2022.persistencia.factories.ReservaRepositoryFactory;
import com.mdgz.dam.labdam2022.persistencia.repositories.AlojamientoRepository;
import com.mdgz.dam.labdam2022.persistencia.repositories.ReservaRepository;
import com.mdgz.dam.labdam2022.viewModels.DetalleAlojamientoViewModel;

public class DetalleAlojamientoViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public DetalleAlojamientoViewModelFactory(final Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetalleAlojamientoViewModel.class)) {
            final AlojamientoRepository alojamientoRepository = AlojamientoRepositoryFactory.create(context);
            final ReservaRepository reservaRepository = ReservaRepositoryFactory.create(context);
            //noinspection unchecked
            return (T) new DetalleAlojamientoViewModel(alojamientoRepository, reservaRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
