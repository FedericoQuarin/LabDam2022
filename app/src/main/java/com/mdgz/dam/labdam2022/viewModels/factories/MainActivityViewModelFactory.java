package com.mdgz.dam.labdam2022.viewModels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mdgz.dam.labdam2022.viewModels.MainActivityViewModel;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    public MainActivityViewModelFactory() {

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            //noinspection unchecked
            return (T) new MainActivityViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
