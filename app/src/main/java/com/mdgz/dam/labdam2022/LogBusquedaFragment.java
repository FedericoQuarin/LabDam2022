package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentLogBusquedaBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogBusquedaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Nombre del archivo
    private String FILENAME = "logs";
    // Contexto
    private Context ctx;

    private FragmentLogBusquedaBinding binding;

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
        binding = FragmentLogBusquedaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Me guardo el contexto para leer el archivo
        this.ctx = view.getContext();

        // Cargo el JSON
        cargarJSON();
    }

    public void cargarJSON(){
        try{
            JSONObject log = (JSONObject) new JSONTokener(this.leerDeArchivo()).nextValue();

            binding.txtViewIDLog.setText(log.getString("ID Log"));
            binding.txtViewTimestamp.setText(log.getString("Timestamp de busqueda"));
            binding.txtViewCantidadResultados.setText(log.getString("Cantidad de resultados"));
            binding.txtViewTiempoDeBusqueda.setText(log.getString("Tiempo de busqueda"));

            JSONArray criteriosDeBusqueda = log.getJSONArray("Criterios de busqueda");

            String switchHoteles = criteriosDeBusqueda.get(0).toString();
            String switchDepartamentos = criteriosDeBusqueda.get(1).toString();
            String capacidad = criteriosDeBusqueda.get(2).toString();
            String ciudad = criteriosDeBusqueda.get(3).toString();
            String precioMinimo = criteriosDeBusqueda.get(4).toString();
            String precioMaximo = criteriosDeBusqueda.get(5).toString();
            String switchWiFi = criteriosDeBusqueda.get(6).toString();

            String criteriosDeBusquedaString =  switchHoteles.substring(1, switchHoteles.length()-1) + "\n" +
                                                switchDepartamentos.substring(1, switchDepartamentos.length()-1) + "\n" +
                                                capacidad.substring(1, capacidad.length()-1) + "\n" +
                                                ciudad.substring(1, ciudad.length()-1) + "\n" +
                                                precioMinimo.substring(1, precioMinimo.length()-1) + "\n" +
                                                precioMaximo.substring(1, precioMaximo.length()-1) + "\n" +
                                                switchWiFi.substring(1, switchWiFi.length()-1);

            binding.txtViewCriteriosDeBusqueda.setText(criteriosDeBusquedaString);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private String leerDeArchivo(){
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
            }

            fis.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return sb.toString();
    }
}