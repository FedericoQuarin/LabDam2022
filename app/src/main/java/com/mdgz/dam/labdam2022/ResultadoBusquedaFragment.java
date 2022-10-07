package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.recyclerView.AlojamientoRecyclerAdapter;

public class ResultadoBusquedaFragment extends Fragment implements AlojamientoRecyclerAdapter.OnNoteListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private GestorAlojamiento gestorAlojamiento;

    private FragmentResultadoBusquedaBinding binding;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private String mParam1;
    private String mParam2;

    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }

    public static ResultadoBusquedaFragment newInstance(String param1, String param2) {
        ResultadoBusquedaFragment fragment = new ResultadoBusquedaFragment();
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

        recyclerView = binding.recyclerAlojamiento;
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AlojamientoRecyclerAdapter(gestorAlojamiento.getAlojamientos(), this);
        recyclerView.setAdapter(adapter);

        recyclerView.setClickable(true);
        binding.labelResultadoBusqueda.setText("Existen " + adapter.getItemCount() + " alojamientos que cumplen los filtros seleccionados.");
    }

    @Override
    public void onNoteClick(int position) {
        NavHostFragment.findNavController(ResultadoBusquedaFragment.this)
                .navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment); //TODO: Faltaría la animación
    }

}