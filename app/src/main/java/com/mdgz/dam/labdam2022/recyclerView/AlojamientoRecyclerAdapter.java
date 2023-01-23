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

    private final static Integer CAMBIADO_FAVORITOS = 1;

    protected List<Alojamiento> alojamientos;

    private OnNoteListener onNoteListener;
    private OnFavoriteChangedListener favoriteChangedListener;

    public AlojamientoRecyclerAdapter(List<Alojamiento> alojamientos, OnNoteListener onNoteListener){
        this.alojamientos = alojamientos;
        this.onNoteListener = onNoteListener;

        this.favoriteChangedListener = (posicion, nuevoEstado) -> {};
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
    public void onBindViewHolder(@NonNull AlojamientoViewHolder alojamientoHolder, int position, @NonNull List<Object> payloads) {
        if(!payloads.isEmpty()) {
            if (payloads.get(0) instanceof Integer) {
                if (payloads.get(0).equals(CAMBIADO_FAVORITOS)) {
                    cambiarDrawableFavorito(alojamientoHolder, position);
                }
            }
        }else {
            super.onBindViewHolder(alojamientoHolder, position, payloads);
        }
    }

    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, int position) {
        Alojamiento alojamiento = this.alojamientos.get(position);

        alojamientoHolder.titulo.setText(alojamiento.getTitulo());
        alojamientoHolder.ubicacion.setText(alojamiento.getUbicacion().toString());
        if (alojamiento.getCapacidad() == 1) alojamientoHolder.capacidad.setText("1 persona");
        else alojamientoHolder.capacidad.setText(alojamiento.getCapacidad() + " personas");
        alojamientoHolder.precio.setText("$" + alojamiento.getPrecioBase());
        alojamientoHolder.imagen.setImageResource(R.drawable.hotel_lp_012_1200x498);

        cambiarDrawableFavorito(alojamientoHolder, position);

        alojamientoHolder.botonFavorito.setOnClickListener((v) -> {
            favoriteChangedListener.onChanged(position, !alojamiento.getEsFavorito());
        });

        alojamientoHolder.card.setTransitionName(alojamiento.getId().toString());

    }

    @Override
    public int getItemCount() {
        return this.alojamientos.size();
    }

    public Alojamiento getItem(int posicion) {
        return this.alojamientos.get(posicion);
    }


    public void setData(List<Alojamiento> alojamientos) {
        this.alojamientos = alojamientos;
        notifyItemRangeChanged(0, alojamientos.size());
    }

    public void favoritoCambiado(int pos, boolean newValue) {
        alojamientos.get(pos).setEsFavorito(newValue);
        notifyItemChanged(pos, CAMBIADO_FAVORITOS);
    }

    public interface OnNoteListener{
        void onNoteClick(int posicion, UUID idAlojamiento);
    }

    public interface OnFavoriteChangedListener {
        void onChanged(int posicion, boolean nuevoEstado);
    }

    public void setOnFavoriteChangedListener(OnFavoriteChangedListener listener) {
        this.favoriteChangedListener = listener;
    }

    private void cambiarDrawableFavorito(AlojamientoViewHolder alojamientoHolder, int position) {
        if(alojamientos.get(position).getEsFavorito()) alojamientoHolder.botonFavorito.setButtonDrawable(R.drawable.corazon_lleno);
        else alojamientoHolder.botonFavorito.setButtonDrawable(R.drawable.corazon_vacio);
    }
}
