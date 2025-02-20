package com.mdgz.dam.labdam2022.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.RecyclerViewBusquedaAlojamientosBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.List;
import java.util.UUID;

public class AlojamientoRecyclerAdapter
        extends RecyclerView.Adapter<AlojamientoRecyclerAdapter.AlojamientoViewHolder> {

    private List<Alojamiento> alojamientos;

    private OnNoteListener onNoteListener;

    public AlojamientoRecyclerAdapter(List<Alojamiento> alojamientos, OnNoteListener onNoteListener){
        this.alojamientos = alojamientos;
        this.onNoteListener = onNoteListener;
    }

    public class AlojamientoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView card;
        TextView titulo;
        TextView capacidad;
        TextView precio;
        TextView ubicacion;
        ImageView imagen;
        CheckBox botonFavorito;

        OnNoteListener onNoteListener;

        public AlojamientoViewHolder(RecyclerViewBusquedaAlojamientosBinding binding, OnNoteListener onNoteListener) {
            super(binding.getRoot());
            this.card = binding.cardResultados;
            this.titulo = binding.txtNombreRecyclerView;
            this.capacidad = binding.txtCapacidadRecyclerView;
            this.precio = binding.txtPrecioRecyclerView;
            this.ubicacion = binding.txtUbicacionRVBusquedaAlojamiento;
            this.imagen = binding.imagenAlojamiento;
            this.botonFavorito = binding.buttonFavorito;

            this.card.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View v) {
            this.onNoteListener.onNoteClick(getBindingAdapterPosition(), alojamientos.get(getBindingAdapterPosition()).getId());
        }
    }

    @NonNull
    @Override
    public AlojamientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlojamientoViewHolder(RecyclerViewBusquedaAlojamientosBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false), this.onNoteListener);
    }


    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, int position) {
        Alojamiento alojamiento = this.alojamientos.get(position);
        String ubicacion = alojamiento.getUbicacion().getCalle() + " "
                         + alojamiento.getUbicacion().getNumero() + ", "
                         + alojamiento.getUbicacion().getCiudad().getNombre();

        alojamientoHolder.titulo.setText(alojamiento.getTitulo());
        alojamientoHolder.ubicacion.setText(ubicacion);
        if (alojamiento.getCapacidad() == 1) alojamientoHolder.capacidad.setText("1 persona");
        else alojamientoHolder.capacidad.setText(alojamiento.getCapacidad() + " personas");
        alojamientoHolder.precio.setText("$" + alojamiento.getPrecioBase());
        alojamientoHolder.imagen.setImageResource(R.drawable.hotel_lp_012_1200x498);

        if(alojamiento.getEsFavorito()) alojamientoHolder.botonFavorito.setButtonDrawable(R.drawable.corazon_lleno);

        alojamientoHolder.botonFavorito.setOnClickListener((v) -> {
            if(alojamiento.getEsFavorito()) alojamientoHolder.botonFavorito.setButtonDrawable(R.drawable.corazon_vacio);
            else alojamientoHolder.botonFavorito.setButtonDrawable(R.drawable.corazon_lleno);

            alojamiento.turnFavorito();

        });

        alojamientoHolder.card.setTransitionName(alojamiento.getId().toString());

    }

    @Override
    public int getItemCount() {
        return this.alojamientos.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int posicion, UUID idAlojamiento);
    }
}
