package com.mdgz.dam.labdam2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
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
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        MaterialToolbar toolbar = binding.materialToolbar;

        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(toolbar);


        NavController navController = NavHostFragment.findNavController(binding.fragmentContainerView.getFragment());
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();

        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean("temaOscuro", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        testing();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.boton_buscar:
                NavHostFragment.findNavController(binding.fragmentContainerView.getFragment())
                        .navigate(R.id.action_global_busquedaFragment);
                return true;
            case R.id.mis_reservas:
                Toast.makeText(this, "Reserva", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mis_favoritos:
                Toast.makeText(this, "Favoritos", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opciones:
                NavHostFragment.findNavController(binding.fragmentContainerView.getFragment())
                        .navigate(R.id.action_global_settingsFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            Ubicacion ubicacion1 = new Ubicacion(50, 30, "Balcarce", "1442", ciudad1);

            Hotel hotel = new Hotel(1, "Hotel principal", 1, ubicacion);

            String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed rutrum suscipit mauris. Praesent imperdiet ultrices euismod.";
            String descripcionDepto1 = "El Departamento Santa Fe Boulevard ofrece alojamiento con wifi gratis y vistas al jardín en Santa Fe, a solo 1,7 km de la Universidad Nacional del Litoral y a 700 metros de la Plazoleta. El alojamiento se encuentra a 27 km de la terminal de micros de Paraná y a 28 km de la plaza de Mayo.\n" +
                    "Departamento con aire acondicionado, 2 dormitorios, TV de pantalla plana y cocina.\n" +
                    "Cerca del departamento hay varios lugares de interés, como la plaza de las Banderas, Emilio Zola y la plaza Francia. El aeropuerto más cercano es el de Sauce Viejo, ubicado a 21 km del Departamento Santa Fe Boulevard.";

            Alojamiento d1 = new Departamento(1, "Departamento Santa Fe Boulevard", descripcionDepto1, 2, 1000.00, false, 200.00, 1, ubicacion1, false);
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