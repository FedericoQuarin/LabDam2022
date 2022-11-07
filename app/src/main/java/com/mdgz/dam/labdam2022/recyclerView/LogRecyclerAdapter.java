package com.mdgz.dam.labdam2022.recyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.databinding.RecyclerViewBusquedaLogsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LogRecyclerAdapter extends RecyclerView.Adapter<LogRecyclerAdapter.LogViewHolder>{

    private List<JSONObject> logs;

    public LogRecyclerAdapter(List<JSONObject> logs){

        this.logs = logs;
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{

        public CardView card;
        TextView IDLog;
        TextView timestamp;
        TextView criteriosDeBusqueda;
        TextView cantidadResultados;
        TextView tiempoDeBusqueda;

        public LogViewHolder(RecyclerViewBusquedaLogsBinding binding){
            super(binding.getRoot());
            this.card = binding.cardLogs;
            this.IDLog = binding.txtViewIDLog;
            this.timestamp = binding.txtViewTimestamp;
            this.criteriosDeBusqueda = binding.txtViewCriteriosDeBusqueda;
            this.cantidadResultados = binding.txtViewCantidadResultados;
            this.tiempoDeBusqueda = binding.txtViewTiempoDeBusqueda;
        }
    }

    @NonNull
    @Override
    public LogRecyclerAdapter.LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LogRecyclerAdapter.LogViewHolder(RecyclerViewBusquedaLogsBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(LogRecyclerAdapter.LogViewHolder logHolder, int position) {
        try{
            JSONObject log = this.logs.get(position);

            logHolder.IDLog.setText(log.getString("ID Log"));
            logHolder.timestamp.setText(log.getString("Timestamp de busqueda"));
            logHolder.cantidadResultados.setText(log.getString("Cantidad de resultados"));
            logHolder.tiempoDeBusqueda.setText(log.getString("Tiempo de busqueda"));

            JSONArray criteriosDeBusqueda = log.getJSONArray("Criterios de busqueda");

            String switchHoteles = criteriosDeBusqueda.get(0).toString();
            String switchDepartamentos = criteriosDeBusqueda.get(1).toString();
            String capacidad = criteriosDeBusqueda.get(2).toString();
            String ciudad = criteriosDeBusqueda.get(3).toString();
            String precioMinimo = criteriosDeBusqueda.get(4).toString();
            String precioMaximo = criteriosDeBusqueda.get(5).toString();
            String switchWiFi = criteriosDeBusqueda.get(6).toString();

            String criteriosDeBusquedaString =
                    "- " + switchHoteles.substring(1, switchHoteles.length()-1) + "\n" +
                    "- " + switchDepartamentos.substring(1, switchDepartamentos.length()-1) + "\n" +
                    "- " + capacidad.substring(1, capacidad.length()-1) + "\n" +
                    "- " + ciudad.substring(1, ciudad.length()-1) + "\n" +
                    "- " + precioMinimo.substring(1, precioMinimo.length()-1) + "\n" +
                    "- " + precioMaximo.substring(1, precioMaximo.length()-1) + "\n" +
                    "- " + switchWiFi.substring(1, switchWiFi.length()-1);

            logHolder.criteriosDeBusqueda.setText(criteriosDeBusquedaString);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount(){

        return this.logs.size();
    }
}

