package com.mdgz.dam.labdam2022;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mdgz.dam.labdam2022.databinding.FragmentMisReservasBinding;
import com.mdgz.dam.labdam2022.recyclerView.ReservaRecyclerAdapter;

public class MisReservasFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private FragmentMisReservasBinding binding;

    private String mParam1;
    private String mParam2;

    public MisReservasFragment() {
        // Required empty public constructor
    }

    public static MisReservasFragment newInstance(String param1, String param2) {
        MisReservasFragment fragment = new MisReservasFragment();
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
        binding = FragmentMisReservasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.recyclerView = binding.recyclerMisReservas;
        this.layoutManager = new LinearLayoutManager(view.getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        this.adapter = new ReservaRecyclerAdapter();
        this.recyclerView.setAdapter(adapter);

        this.recyclerView.setClickable(false);
    }

}

