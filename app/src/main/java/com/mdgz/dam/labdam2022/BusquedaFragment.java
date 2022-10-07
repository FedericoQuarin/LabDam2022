package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.gestores.GestorCiudad;
import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusquedaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusquedaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentBusquedaBinding binding;


    private SeekBar seekBarCapacidad;
    private TextView txtViewCapacidad;
    private Button buttonBuscar;
    private Button buttonLimpiar;
    private Spinner spinner_ciudades;
    private Switch switchHoteles;
    private Switch switchDeptos;
    private Switch switchWifi;
    private EditText editTxtPrecioMaximo;
    private EditText editTxtPrecioMinimo;


    private GestorCiudad gestorCiudad;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BusquedaFragment() {
        // Required empty public constructor
    }

    public static BusquedaFragment newInstance(String param1, String param2) {
        BusquedaFragment fragment = new BusquedaFragment();
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

        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seekBarCapacidad = binding.seekBarCapacidad;
        txtViewCapacidad = binding.txtViewCapacidad;
        buttonBuscar = binding.buttonBuscar;
        buttonLimpiar = binding.buttonLimpiar;
        spinner_ciudades = binding.spinnerCiudades;

        switchHoteles = binding.switchHoteles;
        switchDeptos = binding.switchDepartamentos;
        switchWifi = binding.switchWiFi;
        editTxtPrecioMaximo = binding.editTxtPrecioMaximo;
        editTxtPrecioMinimo = binding.editTxtPrecioMinimo;


        gestorCiudad = GestorCiudad.getInstance();

        // Se setea el numero de personas inicial en el correspondiente TextView
        // y se coloca un listener para mantenerlo actualizado
        txtViewCapacidad.setText(seekBarCapacidad.getProgress() + " personas");
        seekBarCapacidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtViewCapacidad.setText(i + " personas");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // TODO validaciones y filtrado de alojamientos
        buttonBuscar.setOnClickListener(v -> {
            NavHostFragment.findNavController(BusquedaFragment.this)
                .navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment);
        });

        // Se buscan las ciudades existentes y se crea el adapter para el spinner
        List<String> ciudades = gestorCiudad.getNombresCiudades();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item,
                ciudades);
        spinner_ciudades.setAdapter(adapter);

        // Se setea el listener del boton "Limpiar", que limpia todos los campos cargados
        buttonLimpiar.setOnClickListener(v -> {
            seekBarCapacidad.setProgress(getContext()
                    .getResources()
                    .getInteger(R.integer.seekbar_capacidad_def_value));
            switchHoteles.setChecked(false);
            switchDeptos.setChecked(false);
            switchWifi.setChecked(false);
            editTxtPrecioMaximo.setText("");
            editTxtPrecioMinimo.setText("");
        });
    }


}