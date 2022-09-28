package com.mdgz.dam.labdam2022.recyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
        this.alojamientos = alojamientos;
    }

    public class AlojamientoViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView capacidad;
        TextView precio;
        TextView ubicacion;
        ImageView imagen;
        ImageButton botonFavorito;
        Boolean esFavorito = false;

        public AlojamientoViewHolder(RecyclerViewBusquedaAlojamientosBinding binding) {
            super(binding.getRoot());
            this.titulo = binding.txtNombreRecyclerView;
            this.capacidad = binding.txtCapacidadRecyclerView;
            this.precio = binding.txtPrecioRecyclerView;
            this.ubicacion = binding.txtUbicacionRecyclerView;
            this.imagen = binding.imagenAlojamiento;
            this.botonFavorito = binding.imageButtonFavorito;
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
        String ubicacion = alojamiento.getUbicacion().getCalle() + " "
                         + alojamiento.getUbicacion().getNumero() + ", "
                         + alojamiento.getUbicacion().getCiudad().getNombre();

        alojamientoHolder.titulo.setText(alojamiento.getTitulo());
        alojamientoHolder.ubicacion.setText(ubicacion);
        alojamientoHolder.capacidad.setText(String.valueOf(alojamiento.getCapacidad()) + " personas");
        alojamientoHolder.precio.setText("$" + String.valueOf(alojamiento.getPrecioBase()));
        alojamientoHolder.imagen.setImageResource(R.drawable.depto_prueba);

        //if(alojamientoHolder.esFavorito) alojamientoHolder.botonCorazon.setImageResource(R.drawable.corazon_vacio);
        //else alojamientoHolder.botonCorazon.setImageResource(R.drawable.corazon_lleno);

        alojamientoHolder.botonFavorito.setOnClickListener((v) -> {

            if(alojamientoHolder.esFavorito) alojamientoHolder.botonFavorito.setImageResource(R.drawable.corazon_vacio);
            else alojamientoHolder.botonFavorito.setImageResource(R.drawable.corazon_lleno);

            alojamientoHolder.esFavorito = !alojamientoHolder.esFavorito;

        });
    }

    @Override
    public int getItemCount() {
        return this.alojamientos.size();
    }

}
