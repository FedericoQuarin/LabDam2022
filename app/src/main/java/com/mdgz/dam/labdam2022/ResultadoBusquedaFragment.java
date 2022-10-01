package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.recyclerView.AlojamientoRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadoBusquedaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadoBusquedaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private GestorAlojamiento gestorAlojamiento;

    private FragmentResultadoBusquedaBinding binding;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultadoBusquedaFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        adapter = new AlojamientoRecyclerAdapter(gestorAlojamiento.getAlojamientos());
        recyclerView.setAdapter(adapter);
    }
}