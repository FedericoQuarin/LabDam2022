package com.mdgz.dam.labdam2022;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.transition.MaterialContainerTransform;
import com.mdgz.dam.labdam2022.databinding.DetalleAlojamientoDeptoBinding;
import com.mdgz.dam.labdam2022.databinding.DetalleAlojamientoHotelBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.gestores.GestorReserva;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.viewModels.DetalleAlojamientoViewModel;
import com.mdgz.dam.labdam2022.viewModels.MainActivityViewModel;
import com.mdgz.dam.labdam2022.viewModels.factories.DetalleAlojamientoViewModelFactory;
import com.mdgz.dam.labdam2022.viewModels.factories.MainActivityViewModelFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DetalleAlojamientoFragment extends Fragment {
    private FragmentDetalleAlojamientoBinding binding;
    private DetalleAlojamientoDeptoBinding bindingDepto;
    private DetalleAlojamientoHotelBinding bindingHotel;

    private DetalleAlojamientoViewModel viewModel;
    private MainActivityViewModel viewModelMainActivity;
    private String idAlojamiento;

    private GestorReserva gestorReserva;

    private Alojamiento alojamiento;
    private Boolean fechaValida = false;
    private int cantidadPersonas = 0;
    private Double montoTotal;
    private Pair<Long, Long> periodoSeleccionado;

    private Button botonFecha;
    private TextView precioFinal;
    private ImageButton botonMas;
    private ImageButton botonMenos;
    private TextView txtViewCantidadPersonas;
    private TextView txtViewCapacidadAlojamiento;
    private Button botonReservar;
    private TextView descripcion;
    private Button buttonMasDescripcion;

    private Calendar calendar;
    private MaterialDatePicker<Pair<Long, Long>> materialDatePicker;

    private Integer tamDescripcionAcotada;

    // TODO ver el tema de usuarios
    private Usuario usuario;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Infla el layout de este fragmento
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater, container, false);
        FrameLayout frameLayoutDetalleDepto = binding.frameLayoutDetalleDepto;
        FrameLayout frameLayoutDetalleHotel = binding.frameLayoutDetalleHotel;


        if (getArguments() != null) {
            // Busca el alojamiento a mostrar
            idAlojamiento = getArguments().getString("idAlojamiento");
            //gestorAlojamiento = GestorAlojamiento.getInstance(getContext());
            //alojamiento = gestorAlojamiento.getAlojamiento(UUID.fromString(stringIdAlojamiento));

            // Infla parte de la interfaz que es especifica del tipo de alojamiento
            bindingDepto = DetalleAlojamientoDeptoBinding.inflate(inflater, frameLayoutDetalleDepto, false);
            frameLayoutDetalleDepto.addView(bindingDepto.getRoot());

            bindingHotel = DetalleAlojamientoHotelBinding.inflate(inflater, frameLayoutDetalleHotel, false);
            frameLayoutDetalleHotel.addView(bindingHotel.getRoot());
        }

        View fragmentView = binding.getRoot();

        configurarDateRangePicker();

        // Se asigna el nombre de la transicion
        fragmentView.setTransitionName(idAlojamiento);

        // Se crea la transicion de entrada a este fragmento desde la pestaña resultadosBusqueda
        MaterialContainerTransform containerTransform = new MaterialContainerTransform();
        containerTransform.setDrawingViewId(R.id.fragmentContainerView);
        containerTransform.setScrimColor(Color.TRANSPARENT);
        containerTransform.setDuration(getContext().getResources().getInteger(R.integer.transition_time_container_transform));

        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(com.google.android.material.R.attr.backgroundColor, typedValue, true);
        containerTransform.setAllContainerColors(typedValue.data);

        // Se setea la transicion
        setSharedElementEnterTransition(containerTransform);

        return fragmentView;
    }

    // TODO: guardar estado
    @Override
    public void onViewCreated(@NonNull View fragmentView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(fragmentView, savedInstanceState);
        tamDescripcionAcotada = this.getContext().getResources().getInteger(R.integer.tam_descripcion_acotada);
        // Gestores restantes
        gestorReserva = GestorReserva.getInstance();

        // Variables
        botonFecha = binding.buttonFecha;
        precioFinal = binding.txtPrecioFinalDetalleAlojamiento;
        botonMenos = binding.imageButtonMenos;
        botonMas = binding.imageButtonMas;
        txtViewCantidadPersonas = binding.textViewCantidadPersonasEnReserva;
        txtViewCapacidadAlojamiento = binding.textViewCapacidadAlojamiento;
        botonReservar = binding.buttonReservar;
        descripcion = binding.txtViewDescripcion;
        buttonMasDescripcion = binding.buttonMasDescripcion;

        usuario = new Usuario(UUID.fromString(getString(R.string.id_usuario_pruebas)),
                "Pedrito",
                "pedrito@gmail.com",
                new ArrayList<>(),
                new ArrayList<>());

        // Se busca el viewModel correspondiente al fragmento
        viewModel = new ViewModelProvider(this, new DetalleAlojamientoViewModelFactory(getContext())).get(
                DetalleAlojamientoViewModel.class);

        // Se setea el observer sobre el alojamiento
        viewModel.alojamiento.observe(getViewLifecycleOwner(), alojamiento -> {
            this.alojamiento = alojamiento;
            setearInfoAlojamiento();
        });

        // Se setea el observer sobre la variable reservaExitosa
        viewModel.reservaExitosa.observe(getViewLifecycleOwner(), reservaExitosa -> {
            if (reservaExitosa) {
                volverAPantallaBusqueda();
            }
        });

        viewModel.errorReservar.observe(getViewLifecycleOwner(), throwable -> {
            if (throwable != null) {
                snackbarReservaFallida(fragmentView);
                viewModel.observadoErrorReservar();
            }
        });

        // Se busca el viewModel correspondiente a la actividad
        viewModelMainActivity = new ViewModelProvider(requireActivity(), new MainActivityViewModelFactory()).get(
                MainActivityViewModel.class);

        viewModel.setearUsuario(usuario.getId());

        viewModelMainActivity.alojamientoSeleccionado.observe(getViewLifecycleOwner(), alojamientoSeleccionado -> {
            viewModel.setearAlojamiento(alojamientoSeleccionado);
        });

        botonFecha.setOnClickListener(v -> {
            botonFecha.setClickable(false);
            materialDatePicker.show(getActivity().getSupportFragmentManager(), "Date_picker");
        });

        materialDatePicker.addOnDismissListener( v -> {
            botonFecha.setClickable(true);
            actualizarBotonesYLabel();
        });

        botonMenos.setEnabled(false);

    }

    public void setearInfoAlojamiento() {
        // Armar ubicacion a mostrar
        String ubicacion = alojamiento.getUbicacion().getCalle() + " "
                + alojamiento.getUbicacion().getNumero() + ", "
                + alojamiento.getUbicacion().getCiudad().getNombre();

        binding.txtTituloDetalleAlojamiento.setText(alojamiento.getTitulo());
        binding.txtUbicacionDetalleAlojamiento.setText(ubicacion);
        binding.txtPrecioDetalleAlojamiento.setText("$" + alojamiento.getPrecioBase() + " por noche");

        if (alojamiento.getCapacidad() == 1) binding.txtCapacidadDetalleAlojamiento.setText("1 persona");
        else binding.txtCapacidadDetalleAlojamiento.setText(alojamiento.getCapacidad() + " personas");

        if(alojamiento.getEsFavorito()) binding.buttonDetalleFavorito.setButtonDrawable(R.drawable.corazon_lleno);
        else binding.buttonDetalleFavorito.setButtonDrawable(R.drawable.corazon_vacio);

        binding.buttonDetalleFavorito.setOnClickListener((v) -> {
            viewModel.cambiarFavorito(!alojamiento.getEsFavorito());
        });

        // Si el alojamiento es un departamento se setean los parametros del detalleDepto
        // Sino se setean los del detalleHotel
        if (alojamiento instanceof Departamento) {
            binding.frameLayoutDetalleDepto.setVisibility(View.VISIBLE);

            Departamento depto = (Departamento) alojamiento;

            binding.txtViewPrecioLimpieza.setVisibility(View.VISIBLE);
            binding.txtViewPrecioLimpieza.setText("+$" + depto.getCostoLimpieza() + " de limpieza");

            if (depto.getCantidadHabitaciones() == 1)
                bindingDepto.txtViewHabitaciones.setText("1 habitación");
            else
                bindingDepto.txtViewHabitaciones.setText(depto.getCantidadHabitaciones() + " habitaciones");

            if (!depto.getTieneWifi()) {
                bindingDepto.txtViewWifi.setText("No tiene WIFI");
                bindingDepto.txtViewWifi.setCompoundDrawablesWithIntrinsicBounds(R.drawable.wifi_off_white_24, 0, 0, 0);
            }
        }
        else {
            binding.frameLayoutDetalleHotel.setVisibility(View.VISIBLE);

            Habitacion habitacion = (Habitacion) alojamiento;

            if (habitacion.getCamasMatrimoniales() == 0) {
                bindingHotel.txtViewCamasDobles.setVisibility(View.GONE);
            }
            else if (habitacion.getCamasMatrimoniales() == 1) {
                bindingHotel.txtViewCamasDobles.setText("1 cama doble");
            }
            else {
                bindingHotel.txtViewCamasDobles.setText(habitacion.getCamasMatrimoniales() + " camas dobles");
            }

            if (habitacion.getCamasIndividuales() == 0) {
                bindingHotel.txtViewCamasSimples.setVisibility(View.GONE);
            }
            else if (habitacion.getCamasIndividuales() == 1) {
                bindingHotel.txtViewCamasSimples.setText("1 cama simple");
            }
            else {
                bindingHotel.txtViewCamasSimples.setText(habitacion.getCamasIndividuales() + " camas simples");
            }

            if (!habitacion.getTieneEstacionamiento()) {
                bindingHotel.txtViewEstacionamiento.setCompoundDrawablesWithIntrinsicBounds(R.drawable.not_parking_24, 0, 0, 0);
                bindingHotel.txtViewEstacionamiento.setText("Sin estacionamiento");
            }

            bindingHotel.txtViewNombreHotel.setText(habitacion.getHotel().getNombre());
            bindingHotel.ratingBarHotel.setRating(habitacion.getHotel().getCategoria());
        }

        if (alojamiento.getDescripcion().length() <= tamDescripcionAcotada) {
            descripcion.setText(alojamiento.getDescripcion());
            buttonMasDescripcion.setVisibility(View.GONE);
        }
        else {
            colocarDescripcionAcotada();
        }

        txtViewCantidadPersonas.setText(String.valueOf(cantidadPersonas));
        txtViewCapacidadAlojamiento.setText(" / " + alojamiento.getCapacidad());

        botonMenos.setOnClickListener(v -> restarCantidadPersonas());

        botonMas.setOnClickListener(v -> sumarCantidadPersonas());

        botonReservar.setOnClickListener(v -> logicaReservar());
    }

    public void snackbarReservaFallida(View view) {
        Snackbar snackbar = Snackbar.make(view,"Error. No se pudo registrar su reserva.", Snackbar.LENGTH_LONG)
                .setDuration(3000)
                .setAnchorView(binding.bottomAppBar);
        snackbar.show();
    }

    // Actualiza el texto del boton "Fecha de reserva" cuando se selecciona una fecha
    // en el DatePicker
    private void actualizarBotonesYLabel(){
        // Obtener la selecion del range picker
        periodoSeleccionado = (Pair<Long, Long>) materialDatePicker.getSelection();

        try {
            Long longFechaIngreso = periodoSeleccionado.first;
            calendar.setTimeInMillis(longFechaIngreso);
            String fechaIngreso = calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1);
            int anioIngreso = calendar.get(Calendar.YEAR);

            Long longFechaEgreso = periodoSeleccionado.second;
            calendar.setTimeInMillis(longFechaEgreso);
            String fechaEgreso = calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1);
            int anioEgreso = calendar.get(Calendar.YEAR);

            // Si el periodo seleccionado empieza o termina en otro año que no sea el actual
            // se agrega el año de reserva a cada fecha
            if (anioIngreso != LocalDate.now().getYear() || anioEgreso != LocalDate.now().getYear()) {
                fechaIngreso += "/" + anioIngreso;
                fechaEgreso += "/" + anioEgreso;
            }

            botonFecha.setText(fechaIngreso + " - " + fechaEgreso);

            long diferenciaEntreFechas = longFechaEgreso - longFechaIngreso;
            long cantidadNoches = TimeUnit.DAYS.convert(diferenciaEntreFechas, TimeUnit.MILLISECONDS);
            montoTotal = alojamiento.costoTotal(cantidadNoches);
            fechaValida = true;

            if(cantidadPersonas > 0) botonReservar.setEnabled(true);
        }
        catch (NullPointerException npe) {
            botonFecha.setText("Fecha de reserva");
            fechaValida = false;

            botonReservar.setEnabled(false);
            montoTotal = 0.0;
        }

        precioFinal.setText("$ " + montoTotal);
    }

    private void configurarDateRangePicker(){
        // Gestion del DateRangePicker
        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        // Dia de hoy
        long today = calendar.getTimeInMillis();

        // Dia de mañana
        calendar.roll(Calendar.DATE,true);
        long tomorrow = calendar.getTimeInMillis();

        // Mes actual
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        long mes = calendar.getTimeInMillis();

        // Restricciones de calendario
        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();

        constraintBuilder.setStart(mes); // Que no pueda moverme mas atras del mes actual
        constraintBuilder.setValidator(DateValidatorPointForward.now()); // Que solo pueda seleccionar desde hoy en adelante

        // Date picker
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();

        builder.setTitleText("Seleccione el rango de fecha");
        //builder.setSelection(new Pair<>(today,tomorrow));
        builder.setCalendarConstraints(constraintBuilder.build());
        builder.setPositiveButtonText("Guardar");

        materialDatePicker = builder.build();
    }

    private void restarCantidadPersonas(){

        cantidadPersonas--;

        botonMas.setEnabled(true);

        txtViewCantidadPersonas.setText(String.valueOf(cantidadPersonas));

        if(cantidadPersonas == 0) {
            botonMenos.setEnabled(false);
            botonReservar.setEnabled(false);
        }
    }

    private void sumarCantidadPersonas(){

        cantidadPersonas++;

        botonMenos.setEnabled(true);

        txtViewCantidadPersonas.setText(String.valueOf(cantidadPersonas));

        if(cantidadPersonas == alojamiento.getCapacidad()){
            botonMas.setEnabled(false);
        }

        if(fechaValida) {
            botonReservar.setEnabled(true);
        }

    }

    // Genera un AlertDialog para confirmar la reserva
    private void logicaReservar(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
        alerta.setTitle("Confirmar reserva")
                .setMessage("¿Desea confirmar la reserva del alojamiento seleccionado?")
                .setCancelable(false)
                // Si se clickea "Confirmar" se crea la reserva y se vuelve a pantalla de busqueda
                .setPositiveButton("Confirmar", (dialogInterface, i) -> {
                    viewModel.crearReserva(Date.from(Instant.ofEpochMilli(periodoSeleccionado.first+1000)),
                            Date.from(Instant.ofEpochMilli(periodoSeleccionado.second+1000)),
                            cantidadPersonas, montoTotal, alojamiento, usuario);
                })
                // Si se clickea "No" se queda en la pantalla actual
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());

        alerta.create().show();
    }

    public void colocarDescripcionAcotada() {
        descripcion.setText(alojamiento.getDescripcion().substring(0, tamDescripcionAcotada) + "...");
        buttonMasDescripcion.setOnClickListener(v -> colocarDescripcionExtendida());
        buttonMasDescripcion.setText(R.string.button_ver_mas_descripcion);
    }

    public void colocarDescripcionExtendida() {
        descripcion.setText(alojamiento.getDescripcion());
        buttonMasDescripcion.setOnClickListener(v -> colocarDescripcionAcotada());
        buttonMasDescripcion.setText(R.string.button_ver_menos_descripcion);
    }

    public void volverAPantallaBusqueda() {
        Bundle bundle = new Bundle();
        bundle.putInt("tipo", BusquedaFragment.VENTANA_DETALLE);  // TODO: ver tema ID

        viewModelMainActivity.alojamientoSeleccionado.removeObservers(getViewLifecycleOwner());
        viewModelMainActivity.setearAlojamientoSeleccionado(null);

        NavHostFragment.findNavController(DetalleAlojamientoFragment.this)
                .navigate(R.id.action_detalleAlojamientoFragment_to_busquedaFragment, bundle);
    }
}