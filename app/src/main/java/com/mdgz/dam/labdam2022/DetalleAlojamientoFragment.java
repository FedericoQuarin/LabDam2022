package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mdgz.dam.labdam2022.databinding.DetalleAlojamientoDeptoBinding;
import com.mdgz.dam.labdam2022.databinding.DetalleAlojamientoHotelBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;

public class DetalleAlojamientoFragment extends Fragment {
    private FragmentDetalleAlojamientoBinding binding;
    private DetalleAlojamientoDeptoBinding bindingDepto;
    private DetalleAlojamientoHotelBinding bindingHotel;

    private GestorAlojamiento gestorAlojamiento;

    private int idAlojamiento;
    private Alojamiento alojamiento;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout de este fragmento
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater, container, false);
        FrameLayout frameLayout = binding.frameLayout;

        // Busca el alojamiento a mostrar
        idAlojamiento = getArguments().getInt("idAlojamiento");
        gestorAlojamiento = GestorAlojamiento.getInstance();
        alojamiento = gestorAlojamiento.getAlojamiento(idAlojamiento);

        // Infla parte de la interfaz que es especifica del tipo de alojamiento
        if (alojamiento instanceof Departamento) {
            bindingDepto = DetalleAlojamientoDeptoBinding.inflate(inflater, frameLayout, false);
            frameLayout.addView(bindingDepto.getRoot());
        }
        else {
            bindingHotel = DetalleAlojamientoHotelBinding.inflate(inflater, frameLayout, false);
            frameLayout.addView(bindingHotel.getRoot());
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Seteo de elemento del fragmento
        String ubicacion = alojamiento.getUbicacion().getCalle() + " "
                + alojamiento.getUbicacion().getNumero() + ", "
                + alojamiento.getUbicacion().getCiudad().getNombre();

        binding.txtTituloDetalleAlojamiento.setText(alojamiento.getTitulo());
        binding.txtUbicacionDetalleAlojamiento.setText(ubicacion);
        binding.txtPrecioDetalleAlojamiento.setText("$" + alojamiento.getPrecioBase());

        if (alojamiento.getCapacidad() == 1) binding.txtCapacidadDetalleAlojamiento.setText("1 persona");
        else binding.txtCapacidadDetalleAlojamiento.setText(alojamiento.getCapacidad() + " persona");

        if(alojamiento.getEsFavorito()) binding.buttonDetalleFavorito.setButtonDrawable(R.drawable.corazon_lleno);
        else binding.buttonDetalleFavorito.setButtonDrawable(R.drawable.corazon_vacio);

        binding.buttonDetalleFavorito.setOnClickListener((v) -> {
            if(alojamiento.getEsFavorito()) binding.buttonDetalleFavorito.setButtonDrawable(R.drawable.corazon_vacio);
            else binding.buttonDetalleFavorito.setButtonDrawable(R.drawable.corazon_lleno);

            alojamiento.turnFavorito();
        });

        // Si el alojamiento es un departamento se setean los parametros del detalleDepto
        // Sino se setean los del detalleHotel
        if (alojamiento instanceof Departamento) {
            Departamento depto = (Departamento) alojamiento;

            if (depto.getCantidadHabitaciones() == 1)
                bindingDepto.txtViewHabitaciones.setText("1 habitación");
            else
                bindingDepto.txtViewHabitaciones.setText(depto.getCantidadHabitaciones() + " habitaciones");

            if (!depto.getTieneWifi())
                bindingDepto.imageViewWifi.setImageResource(R.drawable.wifi_off_white_24);
        }
        else {
            Habitacion habitacion = (Habitacion) alojamiento;

            if (habitacion.getCamasMatrimoniales() == 0) {
                bindingHotel.layoutCamasDobles.setVisibility(View.GONE);
            }
            else if (habitacion.getCamasMatrimoniales() == 1) {
                bindingHotel.txtViewCamasDobles.setText("1 cama doble");
            }
            else {
                bindingHotel.txtViewCamasDobles.setText(habitacion.getCamasMatrimoniales() + " camas dobles");
            }

            if (habitacion.getCamasIndividuales() == 0) {
                bindingHotel.layoutCamasSimples.setVisibility(View.GONE);
            }
            else if (habitacion.getCamasIndividuales() == 1) {
                bindingHotel.txtViewCamasSimples.setText("1 cama simple");
            }
            else {
                bindingHotel.txtViewCamasSimples.setText(habitacion.getCamasIndividuales() + " camas simples");
            }

            if (!habitacion.getTieneEstacionamiento()) {
                bindingHotel.layoutEstacionamiento.setVisibility(View.GONE);
            }
        }
    }
}