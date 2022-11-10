package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.transition.MaterialElevationScale;
import com.google.android.material.transition.MaterialFade;
import com.google.android.material.transition.MaterialFadeThrough;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.recyclerView.AlojamientoRecyclerAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import java.util.Map;

public class ResultadoBusquedaFragment extends Fragment implements AlojamientoRecyclerAdapter.OnNoteListener{
    private GestorAlojamiento gestorAlojamiento;

    private FragmentResultadoBusquedaBinding binding;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // Nombre del archivo
    private String FILENAME = "logs";
    // Contexto
    private Context ctx;
    // IDLog
    private Integer IDLog = 6;
    // Cantidad de alojamientos encontrados
    private Integer cantidadAlojamientosEncontrados;
    // Lista de criterios de busqueda
    private List<String> ListaCriteriosDeBusqueda = new ArrayList();

    private MaterialElevationScale transicionElevationScale_exit;

    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestorAlojamiento = GestorAlojamiento.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultadoBusquedaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Se pospone la transicion de entrada para que funcione la transicion al volver
        postponeEnterTransition();
        OneShotPreDrawListener.add(view, this::startPostponedEnterTransition);

        // Se setea el recycler view
        recyclerView = binding.recyclerAlojamiento;

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AlojamientoRecyclerAdapter(gestorAlojamiento.getAlojamientos(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setClickable(true);
        
        binding.labelResultadoBusqueda.setText(adapter.getItemCount() + " alojamientos encontrados.");

        // Borra cualquier transicion que se haya colocado previamente
        setExitTransition(null);

        // Se busca el tiempo que deben durar las transiciones a detalle
        int TIEMPO_TRANSICION_A_DETALLE = getResources().getInteger(R.integer.transition_time_container_transform);

        // Se crea la transicion de salida
        transicionElevationScale_exit = new MaterialElevationScale(false);
        transicionElevationScale_exit.setDuration(TIEMPO_TRANSICION_A_DETALLE);
    }

    // Se implementa el metodo de la interfaz OnNoteListener, que se
    // ejecuta cuando se hace click en un elemento del Recycler
    @Override
    public void onNoteClick(int posicion, UUID idAlojamiento) {
        AlojamientoRecyclerAdapter.AlojamientoViewHolder selectedViewHolder =
                (AlojamientoRecyclerAdapter.AlojamientoViewHolder) recyclerView
                .findViewHolderForAdapterPosition(posicion);

        if (selectedViewHolder != null) {
            Bundle bundle = new Bundle();
            bundle.putString("idAlojamiento", idAlojamiento.toString());

            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(selectedViewHolder.card, selectedViewHolder.card.getTransitionName())
                    .build();

            setExitTransition(transicionElevationScale_exit);

            NavHostFragment.findNavController(ResultadoBusquedaFragment.this)
                    .navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment, bundle, null, extras); //TODO: Faltaría la animación
        }
    }

}

