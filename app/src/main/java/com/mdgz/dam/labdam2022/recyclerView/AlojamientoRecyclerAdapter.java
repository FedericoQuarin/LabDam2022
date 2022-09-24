package com.mdgz.dam.labdam2022.recyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.List;

public class AlojamientoRecyclerAdapter
        extends RecyclerView.Adapter<AlojamientoRecyclerAdapter.AlojamientoViewHolder> {

    GestorAlojamiento gA = GestorAlojamiento.getInstance();

    private List<Alojamiento> alojamientos;

    public AlojamientoRecyclerAdapter(List<Alojamiento> aloj){

        Log.d("My tag", "My message");
        this.alojamientos = gA.getListaAlojamientos();
    }

    public class AlojamientoViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView descripcion;
        TextView id;
        TextView capacidad;
        TextView precio;

        public AlojamientoViewHolder(View v) {
            super(v);
            this.nombre = v.findViewById(R.id.txtNombreRecyclerView);
            this.descripcion = v.findViewById(R.id.txtDescripcionRecyclerView);
            this.id = v.findViewById(R.id.txtIdRecyclerView);
            this.capacidad = v.findViewById(R.id.txtCapacidadRecyclerView);
            this.precio = v.findViewById(R.id.txtPrecioRecyclerView);
        }
    }

    @Override
    public AlojamientoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_detalle_alojamiento,parent,false);

        AlojamientoViewHolder aVH = new AlojamientoViewHolder(v);

        return aVH;
    }


    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, int position) {

        Alojamiento alojamiento = this.alojamientos.get(position);

        alojamientoHolder.id.setText(alojamiento.getId());
        alojamientoHolder.nombre.setText(alojamiento.getTitulo());
        alojamientoHolder.descripcion.setText(alojamiento.getDescripcion());
        alojamientoHolder.capacidad.setText(alojamiento.getCapacidad());
        alojamientoHolder.precio.setText(alojamiento.getPrecioBase().toString());

    }

    @Override
    public int getItemCount() {
        return this.alojamientos.size();
    }

}
