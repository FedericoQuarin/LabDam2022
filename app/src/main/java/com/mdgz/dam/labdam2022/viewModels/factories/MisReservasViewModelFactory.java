package com.mdgz.dam.labdam2022.viewModels.factories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mdgz.dam.labdam2022.persistencia.factories.ReservaRepositoryFactory;
import com.mdgz.dam.labdam2022.persistencia.repositories.ReservaRepository;
import com.mdgz.dam.labdam2022.viewModels.MisReservasViewModel;

public class MisReservasViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public MisReservasViewModelFactory(final Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MisReservasViewModel.class)) {
            final ReservaRepository repo = ReservaRepositoryFactory.create(context);
            //noinspection unchecked
            return (T) new MisReservasViewModel(repo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
