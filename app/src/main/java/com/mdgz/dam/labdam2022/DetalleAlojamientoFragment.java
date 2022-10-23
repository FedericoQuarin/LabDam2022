package com.mdgz.dam.labdam2022;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.mdgz.dam.labdam2022.databinding.DetalleAlojamientoDeptoBinding;
import com.mdgz.dam.labdam2022.databinding.DetalleAlojamientoHotelBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.gestores.GestorReserva;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DetalleAlojamientoFragment extends Fragment {
    private FragmentDetalleAlojamientoBinding binding;
    private DetalleAlojamientoDeptoBinding bindingDepto;
    private DetalleAlojamientoHotelBinding bindingHotel;

    private GestorAlojamiento gestorAlojamiento;
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

    private Calendar calendar;
    private MaterialDatePicker<Pair<Long, Long>> materialDatePicker;

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
        FrameLayout frameLayout = binding.frameLayout;

        if (getArguments() != null) {
            // Busca el alojamiento a mostrar
            int idAlojamiento = getArguments().getInt("idAlojamiento");
            gestorAlojamiento = GestorAlojamiento.getInstance();
            alojamiento = gestorAlojamiento.getAlojamiento(idAlojamiento);

            // Infla parte de la interfaz que es especifica del tipo de alojamiento
            if (alojamiento instanceof Departamento) {
                bindingDepto = DetalleAlojamientoDeptoBinding.inflate(inflater, frameLayout, false);
                frameLayout.addView(bindingDepto.getRoot());
            }
            else {
                bindingHotel = DetalleAlojamientoHotelBinding.inflate(inflater, frameLayout, false);
                frameLayout.addView(bindingHotel.getRoot());
            }
        }

        configurarDateRangePicker();

        return binding.getRoot();
    }

    // TODO: guardar estado
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Gestores restantes
        gestorReserva = GestorReserva.getInstance();

        // Seteo de elemento del fragmento
        String ubicacion = alojamiento.getUbicacion().getCalle() + " "
                + alojamiento.getUbicacion().getNumero() + ", "
                + alojamiento.getUbicacion().getCiudad().getNombre();

        // Variables
        botonFecha = binding.buttonFecha;
        precioFinal = binding.txtPrecioFinalDetalleAlojamiento;
        botonMenos = binding.imageButtonMenos;
        botonMas = binding.imageButtonMas;
        txtViewCantidadPersonas = binding.textViewCantidadPersonasEnReserva;
        txtViewCapacidadAlojamiento = binding.textViewCapacidadAlojamiento;
        botonReservar = binding.buttonReservar;
        descripcion = binding.txtViewDescripcion;

        binding.txtTituloDetalleAlojamiento.setText(alojamiento.getTitulo());
        binding.txtUbicacionDetalleAlojamiento.setText(ubicacion);
        binding.txtPrecioDetalleAlojamiento.setText("$" + alojamiento.getPrecioBase() + " por noche");

        if (alojamiento.getCapacidad() == 1) binding.txtCapacidadDetalleAlojamiento.setText("1 persona");
        else binding.txtCapacidadDetalleAlojamiento.setText(alojamiento.getCapacidad() + " personas");

        if(alojamiento.getEsFavorito()) binding.buttonDetalleFavorito.setButtonDrawable(R.drawable.corazon_lleno);

        binding.buttonDetalleFavorito.setOnClickListener((v) -> {
            if(alojamiento.getEsFavorito()) binding.buttonDetalleFavorito.setButtonDrawable(R.drawable.corazon_vacio);
            else binding.buttonDetalleFavorito.setButtonDrawable(R.drawable.corazon_lleno);

            alojamiento.turnFavorito();
        });

        // Si el alojamiento es un departamento se setean los parametros del detalleDepto
        // Sino se setean los del detalleHotel
        if (alojamiento instanceof Departamento) {
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

        descripcion.setText(alojamiento.getDescripcion());

        // TODO: corregir - si se clickea el boton antes de que se abra el datePicker crashea
        botonFecha.setOnClickListener(v -> materialDatePicker.show(getActivity().getSupportFragmentManager(), "Date_picker"));

        materialDatePicker.addOnDismissListener( p -> actualizarBotonesYLabel());

        txtViewCantidadPersonas.setText(String.valueOf(cantidadPersonas));
        txtViewCapacidadAlojamiento.setText(" / " + alojamiento.getCapacidad());

        botonMenos.setEnabled(false);
        botonMenos.setOnClickListener(v -> restarCantidadPersonas());

        botonMas.setOnClickListener(v -> sumarCantidadPersonas());

        botonReservar.setOnClickListener(v -> logicaReservar());
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
        builder.setSelection(new Pair<>(today,tomorrow));
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
                    gestorReserva.crearReserva(Instant.ofEpochMilli(periodoSeleccionado.first),
                            Instant.ofEpochMilli(periodoSeleccionado.second),
                            cantidadPersonas, montoTotal, alojamiento);

                    Bundle bundle = new Bundle();
                    bundle.putInt("tipo", BusquedaFragment.VENTANA_DETALLE);  // TODO: ver tema ID

                    NavHostFragment.findNavController(DetalleAlojamientoFragment.this)
                            .navigate(R.id.action_detalleAlojamientoFragment_to_busquedaFragment, bundle);
                })
                // Si se clickea "No" se queda en la pantalla actual
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());

        alerta.create().show();
    }
}