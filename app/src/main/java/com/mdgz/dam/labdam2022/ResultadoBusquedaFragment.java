package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.recyclerView.AlojamientoRecyclerAdapter;


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
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import java.util.Map;

public class ResultadoBusquedaFragment extends Fragment implements AlojamientoRecyclerAdapter.OnNoteListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

        // Me guardo el contexto para generar el archivo
        this.ctx = view.getContext();

        recyclerView = binding.recyclerAlojamiento;
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AlojamientoRecyclerAdapter(gestorAlojamiento.getAlojamientos(), this);
        recyclerView.setAdapter(adapter);

        recyclerView.setClickable(true);
        //binding.labelResultadoBusqueda.setText("Existen " + adapter.getItemCount() + " alojamientos que cumplen los filtros seleccionados.");

        //Para el JSON
        cantidadAlojamientosEncontrados = adapter.getItemCount();
        binding.labelResultadoBusqueda.setText(cantidadAlojamientosEncontrados + " alojamientos encontrados.");

        // Paso el bundle para generar el JSON
        generarJSON(getArguments());
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


            setExitSharedElementCallback(
                    new SharedElementCallback() {
                        @Override
                        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                            // Map the first shared element name to the child ImageView.
                            sharedElements
                                    .put(names.get(0),
                                            selectedViewHolder.card);
                        }

                    });

            NavHostFragment.findNavController(ResultadoBusquedaFragment.this)
                    .navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment, bundle, null, extras); //TODO: Faltaría la animación
        }
    }

    public JSONObject generarJSON (Bundle bundle) {
        JSONObject archivoJSON = new JSONObject();
        this.IDLog++;
        cargarListaCriteriosDeBusqueda();
        try{
            archivoJSON.put("ID Log", proximoIDLog());
            archivoJSON.put("Timestamp de busqueda", System.currentTimeMillis()/1000);
            archivoJSON.put("Cantidad de resultados", cantidadAlojamientosEncontrados); //TODO: VER
            archivoJSON.put("Tiempo de busqueda", System.currentTimeMillis()/1000 - (Long) bundle.get("tiempoPresionoBuscar"));

            JSONArray criteriosDeBusqueda = new JSONArray();

            for(String cdb : ListaCriteriosDeBusqueda){

                JSONObject CDB_JSON = new JSONObject();
                CDB_JSON.put(cdb, bundle.get(cdb));
                criteriosDeBusqueda.put(CDB_JSON);
            }

            archivoJSON.put("Criterios de busqueda", criteriosDeBusqueda);
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        this.escribirEnArchivo(archivoJSON);

        return archivoJSON;
    }

    private void escribirEnArchivo(JSONObject log){
        FileOutputStream fos = null;

        try{
            fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(log.toString().getBytes());
            fos.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return;
    }

    private void cargarListaCriteriosDeBusqueda(){

        ListaCriteriosDeBusqueda.add("Switch hoteles");
        ListaCriteriosDeBusqueda.add("Switch departamentos");
        ListaCriteriosDeBusqueda.add("Capacidad");
        ListaCriteriosDeBusqueda.add("Ciudad");
        ListaCriteriosDeBusqueda.add("Precio minimo");
        ListaCriteriosDeBusqueda.add("Precio maximo");
        ListaCriteriosDeBusqueda.add("Switch WiFi");
    }

    // Se busca el número de un log ya creado, si existe se copia y se le agrega 1 y si no existe se asigna el valor 1
    private Integer proximoIDLog(){
        Integer IDLog = 1;
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();

        try{
            fis = ctx.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffRdr = new BufferedReader(isr);
            String line = buffRdr.readLine();
            fis.close();

            JSONObject log = (JSONObject) new JSONTokener(line).nextValue();

            IDLog = Integer.parseInt(log.getString("ID Log")) + 1;
        }
        catch (FileNotFoundException e){
            // No hago nada, se devuelve el valor 1
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }


        return IDLog;
    }

}
