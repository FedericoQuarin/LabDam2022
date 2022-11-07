package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaLogBinding;
import com.mdgz.dam.labdam2022.databinding.RecyclerViewBusquedaLogsBinding;
import com.mdgz.dam.labdam2022.recyclerView.LogRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LogBusquedaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // Nombre del archivo
    private String FILENAME = "logs";
    // Contexto
    private Context ctx;

    private FragmentBusquedaLogBinding binding;

    private String mParam1;
    private String mParam2;

    public LogBusquedaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LogBusquedaFragment newInstance(String param1, String param2) {
        LogBusquedaFragment fragment = new LogBusquedaFragment();
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
        binding = FragmentBusquedaLogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Me guardo el contexto para leer el archivo
        this.ctx = view.getContext();

        this.recyclerView = binding.recyclerLog;
        this.layoutManager = new LinearLayoutManager(view.getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        this.adapter = new LogRecyclerAdapter(leerArchivoLogs());
        this.recyclerView.setAdapter(adapter);

        this.recyclerView.setClickable(false);
    }

    private List<JSONObject> leerArchivoLogs(){

        List<JSONObject> listaLogs = new ArrayList<>();

        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();

        try{
            fis = ctx.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffRdr = new BufferedReader(isr);
            String line = buffRdr.readLine();

            while (line != null){
                sb.append(line);
                sb.append(System.lineSeparator());
                line =buffRdr.readLine();

                System.out.println("LO QUE LEE = " + sb.toString());
                JSONObject log = (JSONObject) new JSONTokener(sb.toString()).nextValue();
                listaLogs.add(log);
                sb = new StringBuilder();
            }

            fis.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return listaLogs;
    }
}