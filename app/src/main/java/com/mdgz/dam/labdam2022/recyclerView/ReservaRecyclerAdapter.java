package com.mdgz.dam.labdam2022.recyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.mdgz.dam.labdam2022.databinding.RecyclerViewMisReservasBinding;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.List;

public class ReservaRecyclerAdapter extends RecyclerView.Adapter<ReservaRecyclerAdapter.ReservaViewHolder> {
    private List<Reserva> reservas;

    public ReservaRecyclerAdapter(List<Reserva> reservas){
        this.reservas = reservas;
    }

    public static class ReservaViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView nombreAlojamiento;
        TextView cantidadPersonas;
        TextView direccion;
        TextView precioFinal;

        public ReservaViewHolder(RecyclerViewMisReservasBinding binding){
            super(binding.getRoot());
            this.card = binding.cardReservas;
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
        Reserva reserva = reservas.get(position);

        reservaHolder.nombreAlojamiento.setText(reserva.getAlojamiento().getTitulo());
        reservaHolder.cantidadPersonas.setText(reserva.getCantidadPersonas().toString());
        reservaHolder.direccion.setText(reserva.getAlojamiento().getUbicacion().toString());
        reservaHolder.precioFinal.setText(reserva.getMonto().toString());
    }

    @Override
    public int getItemCount(){

        return reservas.size();
    }
}
