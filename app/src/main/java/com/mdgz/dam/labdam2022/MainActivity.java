package com.mdgz.dam.labdam2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;
import com.mdgz.dam.labdam2022.gestores.GestorAlojamiento;
import com.mdgz.dam.labdam2022.gestores.GestorCiudad;
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

    public void testing() {
        GestorAlojamiento gestorAlojamiento = GestorAlojamiento.getInstance();
        GestorCiudad gestorCiudad = GestorCiudad.getInstance();

        if (gestorCiudad.getCiudades().isEmpty()) {
            Ciudad ciudad1 = new Ciudad(1, "Santa Fe", "SF");
            Ciudad ciudad2 = new Ciudad(2, "Paraná", "PA");
            Ciudad ciudad3 = new Ciudad(3, "Rosario", "RS");
            gestorCiudad.agregarCiudad(ciudad1);
            gestorCiudad.agregarCiudad(ciudad2);
            gestorCiudad.agregarCiudad(ciudad3);

            Ubicacion ubicacion = new Ubicacion(50.0, 30.0, "Calle principal", "1", ciudad1);

            Hotel hotel = new Hotel(1, "Hotel principal", 1, ubicacion);

            String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed rutrum suscipit mauris. Praesent imperdiet ultrices euismod.";

            Alojamiento d1 = new Departamento(1, "Departamento1", "Departamento1 - " + loremIpsum, 1, 100.00, true, 200.00, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d1);
            Alojamiento d2 = new Departamento(2, "Departamento2", "Departamento2 - " + loremIpsum, 2, 200.00, true, 200.00, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d2);
            Alojamiento d3 = new Departamento(3, "Departamento3", "Departamento3 - " + loremIpsum, 3, 300.00, true, 200.00, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d3);
            Alojamiento d4 = new Departamento(4, "Departamento4", "Departamento4 - " + loremIpsum, 4, 400.00, true, 200.00, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d4);
            Alojamiento d5 = new Departamento(5, "Departamento5", "Departamento5 - " + loremIpsum, 5, 500.00, true, 200.00, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d5);
            Alojamiento d6 = new Departamento(6, "Departamento6", "Departamento6 - " + loremIpsum, 6, 600.00, true, 200.00, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d6);

            Alojamiento h1 = new Habitacion(7, "Habitacion1", "Habitacion1 - " + loremIpsum, 1, 100.0, 1, 0, true, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h1);
            Alojamiento h2 = new Habitacion(8, "Habitacion2", "Habitacion2 - " + loremIpsum, 4, 200.0, 0, 2, false, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h2);
            Alojamiento h3 = new Habitacion(9, "Habitacion3", "Habitacion3 - " + loremIpsum, 3, 300.0, 3, 3, true, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h3);
            Alojamiento h4 = new Habitacion(10, "Habitacion4", "Habitacion4 - " + loremIpsum, 4, 400.0, 4, 4, false, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h4);
            Alojamiento h5 = new Habitacion(11, "Habitacion5", "Habitacion5 - " + loremIpsum, 5, 500.0, 5, 5, true, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h5);
            Alojamiento h6 = new Habitacion(12, "Habitacion6", "Habitacion6 - " + loremIpsum, 6, 600.0, 6, 6, false, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h6);
        }
    }
}