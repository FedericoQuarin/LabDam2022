package com.mdgz.dam.labdam2022.recyclerView;

import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.List;

import kotlin.NotImplementedError;

public class MisFavoritosRecyclerAdapter extends AlojamientoRecyclerAdapter {
    public MisFavoritosRecyclerAdapter(List<Alojamiento> alojamientos, OnNoteListener onNoteListener) {
        super(alojamientos, onNoteListener);
    }

    @Override
    public void favoritoCambiado(int pos, boolean newValue) {
        throw new NotImplementedError("No use este metodo. Use favoritoBorrado");
    }

    public void favoritoBorrado(int pos) {
        notifyItemRemoved(pos);
    }
}
