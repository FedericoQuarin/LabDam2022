package com.mdgz.dam.labdam2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        testing();
    }

    public void testing(){

        GestorAlojamiento gA = GestorAlojamiento.getInstance();

        Ciudad ciudad = new Ciudad(1, "Ciudad principal", "C.P.");

        Ubicacion ubicacion = new Ubicacion(50.0, 30.0, "Calle principal", "1", ciudad);

        Hotel hotel = new Hotel(1, "Hotel principal", 1, ubicacion);

        Alojamiento d1 = new Departamento(1, "Departamento1", "", 1, 100.00, true, 200.00, 2, ubicacion);    gA.agregarAlojamiento(d1);
        Alojamiento d2 = new Departamento(2, "Departamento2", "", 2, 200.00, true, 200.00, 2, ubicacion);    gA.agregarAlojamiento(d2);
        Alojamiento d3 = new Departamento(3, "Departamento3", "", 3, 300.00, true, 200.00, 2, ubicacion);    gA.agregarAlojamiento(d3);
        Alojamiento d4 = new Departamento(4, "Departamento4", "", 4, 400.00, true, 200.00, 2, ubicacion);    gA.agregarAlojamiento(d4);
        Alojamiento d5 = new Departamento(5, "Departamento5", "", 5, 500.00, true, 200.00, 2, ubicacion);    gA.agregarAlojamiento(d5);
        Alojamiento d6 = new Departamento(6, "Departamento6", "", 6, 600.00, true, 200.00, 2, ubicacion);    gA.agregarAlojamiento(d6);

        Alojamiento h1 = new Habitacion(1, "Habitacion1", "", 1, 100.0, 1, 1, true, hotel);     gA.agregarAlojamiento(h1);
        Alojamiento h2 = new Habitacion(2, "Habitacion2", "", 2, 200.0, 2, 2, false, hotel);    gA.agregarAlojamiento(h2);
        Alojamiento h3 = new Habitacion(3, "Habitacion3", "", 3, 300.0, 3, 3, true, hotel);     gA.agregarAlojamiento(h3);
        Alojamiento h4 = new Habitacion(4, "Habitacion4", "", 4, 400.0, 4, 4, false, hotel);    gA.agregarAlojamiento(h4);
        Alojamiento h5 = new Habitacion(5, "Habitacion5", "", 5, 500.0, 5, 5, true, hotel);     gA.agregarAlojamiento(h5);
        Alojamiento h6 = new Habitacion(6, "Habitacion6", "", 6, 600.0, 6, 6, false, hotel);    gA.agregarAlojamiento(h6);

    }
}