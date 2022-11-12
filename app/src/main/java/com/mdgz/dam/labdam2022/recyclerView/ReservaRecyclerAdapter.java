package com.mdgz.dam.labdam2022.recyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.mdgz.dam.labdam2022.databinding.RecyclerViewMisReservasBinding;

public class ReservaRecyclerAdapter extends RecyclerView.Adapter<ReservaRecyclerAdapter.ReservaViewHolder> {

    public ReservaRecyclerAdapter(){

    }

    public static class ReservaViewHolder extends RecyclerView.ViewHolder{

        CardView card;
        TextView IDReserva;
        TextView nombreAlojamiento;
        TextView cantidadPersonas;
        TextView direccion;
        TextView precioFinal;

        public ReservaViewHolder(RecyclerViewMisReservasBinding binding){
            super(binding.getRoot());
            this.card = binding.cardReservas;
            this.IDReserva = binding.txtViewIDReserva;
            this.nombreAlojamiento = binding.txtViewNombreAlojamiento;
            this.cantidadPersonas = binding.txtViewCantidadPersonas;
            this.direccion = binding.txtViewDireccion;
            this.precioFinal = binding.txtViewPrecioFinal;
        }
    }

    @NonNull
    @Override
    public ReservaRecyclerAdapter.ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReservaRecyclerAdapter.ReservaViewHolder(RecyclerViewMisReservasBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(ReservaRecyclerAdapter.ReservaViewHolder reservaHolder, int position) {
        /*
        reservaHolder.IDReserva.setText();
        reservaHolder.nombreAlojamiento.setText();
        reservaHolder.cantidadPersonas.setText();
        reservaHolder.direccion.setText();
        reservaHolder.precioFinal.setText();
        */
    }

    @Override
    public int getItemCount(){

        return 1;
    }
}
