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
            "- " + prepararStringSalida(switchHoteles)       + "\n" +
            "- " + prepararStringSalida(switchDepartamentos) + "\n" +
            "- " + prepararStringSalida(capacidad)           + "\n" +
            "- " + prepararStringSalida(ciudad)              + "\n" +
            "- " + prepararStringSalida(precioMinimo)        + "\n" +
            "- " + prepararStringSalida(precioMaximo)        + "\n" +
            "- " + prepararStringSalida(switchWiFi);

            logHolder.criteriosDeBusqueda.setText(criteriosDeBusquedaString);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private String prepararStringSalida(String s){

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ':'){
                s = s.substring(0, i+1) + ' ' + s.substring(i+1);
            }
        }
        return s.replace('"', Character.MIN_VALUE).substring(2, s.length()-1);
    }

    @Override
    public int getItemCount(){

        return this.logs.size();
    }
}

