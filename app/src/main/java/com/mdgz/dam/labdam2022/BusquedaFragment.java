package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.gestores.GestorCiudad;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.viewModels.BusquedaViewModel;
import com.mdgz.dam.labdam2022.viewModels.DetalleAlojamientoViewModel;
import com.mdgz.dam.labdam2022.viewModels.factories.BusquedaViewModelFactory;
import com.mdgz.dam.labdam2022.viewModels.factories.DetalleAlojamientoViewModelFactory;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class BusquedaFragment extends Fragment {
    static final int VENTANA_DETALLE = 1;

    // Gestores
    private GestorCiudad gestorCiudad;

    // Binding
    private FragmentBusquedaBinding binding;

    // Componentes
    private SeekBar seekBarCapacidad;
    private TextView txtViewCapacidad;
    private Button buttonBuscar;
    private Button buttonLimpiar;
    private TextInputLayout list_ciudades;
    private SwitchMaterial switchHoteles;
    private SwitchMaterial switchDeptos;
    private SwitchMaterial switchWifi;
    private EditText editTxtPrecioMaximo;
    private EditText editTxtPrecioMinimo;
    private AutoCompleteTextView editTxtListCiudades;

    // ViewModel
    private BusquedaViewModel busquedaViewModel;

    // Nombre del archivo
    private String FILENAME = "logs";
    // Contexto
    private Context ctx;
    // IDLog
    private Integer IDLog;
    // Cantidad de alojamientos encontrados
    private Integer cantidadAlojamientosEncontrados;

    // Variables
    String stringMinimo;
    String stringMaximo;
    Float minimo;
    Float maximo;

    public BusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Me guardo el contexto para generar el archivo
        this.ctx = view.getContext();

        seekBarCapacidad = binding.seekBarCapacidad;
        txtViewCapacidad = binding.txtViewCapacidad;
        buttonBuscar = binding.buttonBuscar;
        buttonLimpiar = binding.buttonLimpiar;
        list_ciudades = binding.listCiudades;
        editTxtListCiudades = (AutoCompleteTextView) list_ciudades.getEditText();

        switchHoteles = binding.switchHoteles;
        switchDeptos = binding.switchDepartamentos;
        switchWifi = binding.switchWiFi;
        editTxtPrecioMaximo = binding.txtInputLayoutMaximo.getEditText();
        editTxtPrecioMinimo = binding.txtInputLayoutMinimo.getEditText();

        // Si existe estado previo del fragmento, se restaura
        if (savedInstanceState != null) {
            editTxtPrecioMinimo.setText(savedInstanceState.getString("minimo"));
            editTxtPrecioMaximo.setText(savedInstanceState.getString("maximo"));
            editTxtListCiudades.setText(savedInstanceState.getString("ciudad"));
        }

        // Ver si se volvio de la ventana reservas, en cuyo caso se muestra un snackBar
        if (getArguments() != null) {
            int tipoBundle = getArguments().getInt("tipo");
            if (tipoBundle == VENTANA_DETALLE) {
                snackbarReservaExitosa(view);
            }
        }

        setArguments(null);

        //gestorCiudad = GestorCiudad.getInstance(getContext());

        // Se setea el numero de personas inicial en el correspondiente TextView
        // y se coloca un listener para mantenerlo actualizado
        txtViewCapacidad.setText(seekBarCapacidad.getProgress() + " personas");
        seekBarCapacidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i == 1) txtViewCapacidad.setText(i + " persona");
                else txtViewCapacidad.setText(i + " personas");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Se busca el viewModel de la pantalla
        // TODO ver si podemos cambiar el metodo toString de Ciudad
        busquedaViewModel = new ViewModelProvider(this, new BusquedaViewModelFactory(getContext())).get(
                BusquedaViewModel.class);

        // Se setea el observer sobre ciudadCollection. Este rellena la lista de ciudades.
        busquedaViewModel.ciudadCollection.observe(getViewLifecycleOwner(), ciudades -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                    R.layout.list_item_layout,
                    ciudades.stream().map(Ciudad::getNombre).collect(Collectors.toList()));
            editTxtListCiudades.setAdapter(adapter);
        });


        buttonBuscar.setFocusable(true);
        // TODO validaciones y filtrado de alojamientos
        buttonBuscar.setOnClickListener(v -> {
            if(validarEditTexts()) {
//                Bundle bundle = new Bundle();
                //              bundle.putBoolean("seApretoBotonBuscar", true);
                //            bundle.putBoolean("Switch hoteles", this.switchHoteles.isActivated());
                //          bundle.putBoolean("Switch departamentos", this.switchDeptos.isActivated());
                //        bundle.putInt("Capacidad", this.seekBarCapacidad.getProgress());
                //      bundle.putString("Ciudad", this.list_ciudades.getEditText().getText().toString());
                //    bundle.putString("Precio minimo", this.editTxtPrecioMinimo.getText().toString());
                //  bundle.putString("Precio maximo", this.editTxtPrecioMaximo.getText().toString());
                //bundle.putBoolean("Switch WiFi", this.switchWifi.isActivated());
                //bundle.putLong("tiempoPresionoBuscar", System.currentTimeMillis()/1000);

                generarJSON();

                NavHostFragment.findNavController(BusquedaFragment.this)
                        .navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment);
            }

        });

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
            editTxtListCiudades.setText("");
            editTxtPrecioMaximo.clearFocus();
            editTxtPrecioMinimo.clearFocus();
            editTxtListCiudades.clearFocus();

        });


        // Crea el listener para que se cierre el teclado cuando sale el editText de foco
        View.OnFocusChangeListener focusChangeListener = (view1, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
            }
        };

        editTxtPrecioMinimo.setOnFocusChangeListener(focusChangeListener);
        editTxtPrecioMaximo.setOnFocusChangeListener(focusChangeListener);
    }

    public Boolean validarEditTexts(){
        stringMinimo = editTxtPrecioMinimo.getText().toString();
        stringMaximo = editTxtPrecioMaximo.getText().toString();

        /* Depende de como se implemente el filtro, estaba pensando en que sea un unico metodo
        que busque entre los dos valores, cuando no se setea el minimo que busque desde 0,
        cuando no se setea el maximo que busque desde un valor alto (lo cual me parece raro pero bueno)*/
        // TODO: VER (quizas la peor forma de validar dos campos que hice en mi vida)
        if(TextUtils.isEmpty(stringMinimo) && TextUtils.isEmpty(stringMaximo)){
            minimo = 0.0f;
            maximo = Float.MAX_VALUE;
        } else if (TextUtils.isEmpty(stringMinimo)) {
            minimo = 0.0f;
            maximo = Float.valueOf(stringMaximo);
        } else if (TextUtils.isEmpty(stringMaximo)) {
            minimo = Float.valueOf(stringMinimo);
            maximo = Float.MAX_VALUE;
        } else {
            minimo = Float.valueOf(stringMinimo);
            maximo = Float.valueOf(stringMaximo);

            if(minimo >= maximo){
                editTxtPrecioMaximo.setError("Debe ser mayor al precio mínimo");
                return false;
            }
        }

        return true;
    }

    // Muestra un snackbar informando al usuario de que su reserva fue realizada exitosamente
    // TODO: Muestra un boton para ver su reserva
    private void snackbarReservaExitosa(View view) {
        Snackbar snackbar = Snackbar.make(view,"La reserva se realizó correctamente", Snackbar.LENGTH_LONG)
                .setDuration(5000)
                .setAction("Ver", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: Te tendria que llevar a la seccion de reservas
                    }
                });
        snackbar.show();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (editTxtPrecioMinimo != null) {
            outState.putString("minimo", editTxtPrecioMinimo.getText().toString());
            outState.putString("maximo", editTxtPrecioMaximo.getText().toString());
            outState.putString("ciudad", list_ciudades.getEditText().getText().toString());
        }
    }


    public JSONObject generarJSON () {
        JSONObject archivoJSON = new JSONObject();
        try{
            archivoJSON.put("ID Log", proximoIDLog());
            archivoJSON.put("Timestamp de busqueda", timestampHH_MM_SS());
            archivoJSON.put("Cantidad de resultados", "?");
            archivoJSON.put("Tiempo de busqueda", "-");

            JSONArray criteriosDeBusqueda = new JSONArray();

            JSONObject switchHoteles = new JSONObject();
            JSONObject switchDepartamentos = new JSONObject();
            JSONObject capacidad = new JSONObject();
            JSONObject ciudad = new JSONObject();
            JSONObject precioMinimo = new JSONObject();
            JSONObject precioMaximo = new JSONObject();
            JSONObject switchWiFi = new JSONObject();
            switchHoteles.put("Switch hoteles", binding.switchHoteles.isChecked());
            criteriosDeBusqueda.put(switchHoteles);
            switchDepartamentos.put("Switch departamentos", binding.switchDepartamentos.isChecked());
            criteriosDeBusqueda.put(switchDepartamentos);
            capacidad.put("Capacidad", binding.seekBarCapacidad.getProgress());
            criteriosDeBusqueda.put(capacidad);
            ciudad.put("Ciudad", binding.listCiudades.getEditText().getText().toString());
            criteriosDeBusqueda.put(ciudad);
            precioMinimo.put("Precio minimo", this.editTxtPrecioMinimo.getText().toString());
            criteriosDeBusqueda.put(precioMinimo);
            precioMaximo.put("Precio maximo", this.editTxtPrecioMaximo.getText().toString());
            criteriosDeBusqueda.put(precioMaximo);
            switchWiFi.put("Switch WiFi", binding.switchWiFi.isChecked());
            criteriosDeBusqueda.put(switchWiFi);

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
            fos = ctx.openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(log.toString().getBytes());
            String espacio = "\r\n";
            fos.write(espacio.getBytes());
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

    // Se busca el número del siguiente log a crear, en base al número de líneas en el archivo logs.txt
    private Integer proximoIDLog(){
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        IDLog = 1;

        try{
            fis = ctx.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffRdr = new BufferedReader(isr);
            String line = buffRdr.readLine();

            // El número del IDLog es el número de logs cargados en el archivo (1 log por línea)
            while (line != null){
                IDLog++;
                line =buffRdr.readLine();
            }

            fis.close();
        }
        catch (FileNotFoundException e){
            // No se encuentra el archivo, IDLog = 1
        }
        catch (IOException e){
            // Excepción de entrada/salida, IDLog = 1
        }

        return IDLog;
    }

    private String timestampHH_MM_SS(){
        Long currentTime = System.currentTimeMillis();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

        Date date = new Date(currentTime);

        return simpleDateFormat.format(date);
    }
}