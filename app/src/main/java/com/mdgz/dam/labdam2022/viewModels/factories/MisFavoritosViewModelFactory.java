package com.mdgz.dam.labdam2022.viewModels.factories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mdgz.dam.labdam2022.persistencia.factories.AlojamientoRepositoryFactory;
import com.mdgz.dam.labdam2022.viewModels.MainActivityViewModel;
import com.mdgz.dam.labdam2022.viewModels.MisFavoritosViewModel;

public class MisFavoritosViewModelFactory implements ViewModelProvider.Factory {
    private Context ctx;

    public MisFavoritosViewModelFactory(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MisFavoritosViewModel.class)) {
            //noinspection unchecked
            return (T) new MisFavoritosViewModel(AlojamientoRepositoryFactory.create(ctx));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
