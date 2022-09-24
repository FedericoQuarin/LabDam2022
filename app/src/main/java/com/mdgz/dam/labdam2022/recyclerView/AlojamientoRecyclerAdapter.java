package com.mdgz.dam.labdam2022.recyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.RecyclerViewBusquedaAlojamientosBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.List;

public class AlojamientoRecyclerAdapter
        extends RecyclerView.Adapter<AlojamientoRecyclerAdapter.AlojamientoViewHolder> {

    private List<Alojamiento> alojamientos;

    public AlojamientoRecyclerAdapter(List<Alojamiento> alojamientos){

        Log.d("My tag", "My message");
        this.alojamientos = alojamientos;
    }

    public class AlojamientoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView capacidad;
        TextView precio;

        public AlojamientoViewHolder(RecyclerViewBusquedaAlojamientosBinding binding) {
            super(binding.getRoot());
            this.nombre = binding.txtNombreRecyclerView;
            this.capacidad = binding.txtCapacidadRecyclerView;
            this.precio = binding.txtPrecioRecyclerView;
        }
    }

    @NonNull
    @Override
    public AlojamientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlojamientoViewHolder(RecyclerViewBusquedaAlojamientosBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }


    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, int position) {
        Alojamiento alojamiento = this.alojamientos.get(position);

        alojamientoHolder.nombre.setText(alojamiento.getTitulo());
        alojamientoHolder.capacidad.setText(String.valueOf(alojamiento.getCapacidad()));
        alojamientoHolder.precio.setText(String.valueOf(alojamiento.getPrecioBase()));
    }

    @Override
    public int getItemCount() {
        return this.alojamientos.size();
    }

}
