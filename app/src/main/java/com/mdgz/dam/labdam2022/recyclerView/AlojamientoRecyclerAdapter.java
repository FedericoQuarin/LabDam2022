package com.mdgz.dam.labdam2022.recyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        // TODO ver si se puede usar binding
        // private RecyclerViewBusquedaAlojamientosBinding binding;

        TextView nombre;
        TextView descripcion;
        TextView id;
        TextView capacidad;
        TextView precio;

        public AlojamientoViewHolder(RecyclerViewBusquedaAlojamientosBinding binding) {
            super(binding.getRoot());
            this.nombre = binding.txtNombreRecyclerView;
            // this.descripcion = v.findViewById(R.id.txtDescripcionRecyclerView);
            // this.id = v.findViewById(R.id.txtIdRecyclerView);
            this.capacidad = binding.txtCapacidadRecyclerView;
            this.precio = binding.labelPrecioRecyclerView;
        }
    }

    @NonNull
    @Override
    public AlojamientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_detalle_alojamiento,parent,false);*/

        AlojamientoViewHolder aVH =
                new AlojamientoViewHolder(RecyclerViewBusquedaAlojamientosBinding
                        .inflate(LayoutInflater.from(parent.getContext())));

        return aVH;
    }


    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, int position) {

        Alojamiento alojamiento = this.alojamientos.get(position);

        // alojamientoHolder.id.setText(alojamiento.getId());
        alojamientoHolder.nombre.setText(alojamiento.getTitulo());
        // alojamientoHolder.descripcion.setText(alojamiento.getDescripcion());
        alojamientoHolder.capacidad.setText(alojamiento.getCapacidad());
        alojamientoHolder.precio.setText(alojamiento.getPrecioBase().toString());
    }

    @Override
    public int getItemCount() {
        return this.alojamientos.size();
    }

}
