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
import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleAlojamientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleAlojamientoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentDetalleAlojamientoBinding binding;

    private GestorAlojamiento gestorAlojamiento;

    // TODO pasar id a este fragmento
    private int idAlojamiento = 7;
    private Alojamiento alojamiento;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleAlojamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleAlojamientoFragment newInstance(String param1, String param2) {
        DetalleAlojamientoFragment fragment = new DetalleAlojamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater, container, false);
        FrameLayout frameLayout = binding.frameLayout;

        gestorAlojamiento = GestorAlojamiento.getInstance();
        alojamiento = gestorAlojamiento.getAlojamiento(idAlojamiento);

        if (alojamiento instanceof Departamento) {
            DetalleAlojamientoHotelBinding bindingHotel = DetalleAlojamientoHotelBinding.inflate(inflater, frameLayout, false);
            frameLayout.addView(bindingHotel.getRoot());
        }
        else {
            DetalleAlojamientoDeptoBinding bindingDepto = DetalleAlojamientoDeptoBinding.inflate(inflater, frameLayout, false);
            frameLayout.addView(bindingDepto.getRoot());
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}