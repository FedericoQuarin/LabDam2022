package com.mdgz.dam.labdam2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
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
import com.google.android.material.transition.MaterialFade;
import com.google.android.material.transition.MaterialFadeThrough;
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

    private MaterialFadeThrough materialFadeThrough;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testing();
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
        configurarTemaOscuro();
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

    private void configurarTemaOscuro(){
        String value = sharedPreferences.getString("tema", "desactivado");
        if (value.equals("desactivado")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else if (value.equals("activado")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

    private void testing() {
        GestorAlojamiento gestorAlojamiento = GestorAlojamiento.getInstance();
        GestorCiudad gestorCiudad = GestorCiudad.getInstance(getApplicationContext());

        if (gestorAlojamiento.getAlojamientos().isEmpty()) {
            Ciudad ciudad1 = gestorCiudad.getCiudad(1);

            Ubicacion ubicacion = new Ubicacion(50.0, 30.0, "Calle principal", "1", ciudad1);
            Ubicacion ubicacion1 = new Ubicacion(50.0, 30.0, "Balcarce", "1442", ciudad1);

            Hotel hotel = new Hotel("Hotel principal", 1, ubicacion);

            String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed rutrum suscipit mauris. Praesent imperdiet ultrices euismod.";

            String descripcionCentricoStudio = "Departamento equipado para 2 a 3 personas, cercano a transporte público, comercio, servicios, parques y barrios típicos. Todo a la mano!\n" +
                    "Estacionamiento de pago en el edificio.\n" +
                    "El alojamiento\n" +
                    "Cómodo departamento en piso 7, orientación Norte, luminoso y cálido. Ambiente temperado con acumulador de calor eléctrico.\n" +
                    "Equipado con TV 42\", cable y wifi.\n" +
                    "Acceso de los huéspedes\n" +
                    "Acceso a sala de eventos y plaza dura en primer piso, en último piso tienen acceso a GYM, quinchos para asados, piscina, jacuzzi y terraza panorámica. Todo sin costo!\n" +
                    "Otros aspectos para tener en cuenta\n" +
                    "Posibilidad de emitir factura";
                    
            String descripcionDepto1 = "El Departamento Santa Fe Boulevard ofrece alojamiento con wifi gratis y vistas al jardín en Santa Fe, a solo 1,7 km de la Universidad Nacional del Litoral y a 700 metros de la Plazoleta. El alojamiento se encuentra a 27 km de la terminal de micros de Paraná y a 28 km de la plaza de Mayo.\n" +
                    "Departamento con aire acondicionado, 2 dormitorios, TV de pantalla plana y cocina.\n" +
                    "Cerca del departamento hay varios lugares de interés, como la plaza de las Banderas, Emilio Zola y la plaza Francia. El aeropuerto más cercano es el de Sauce Viejo, ubicado a 21 km del Departamento Santa Fe Boulevard.";

            String descripcionCalidadPremium = "En el corazon de Puerto Norte, enfrente de Shoping Alto Rosario. Seguridad 24 hs, facil acceso, transportes publicos, excelente zona. Cochera cubierta en el edificio. Precio en dolares.\n" +
                    "El alojamiento\n" +
                    "A cuadras del rio, a cuadras de Pichincha, a cuadras del movimiento de la ciudad, pero con la tranquilidad y seguridad de un complejo privado con todas las comodidades.\n" +
                    "Acceso de los huéspedes\n" +
                    "Lo mejor en el mejor lugar de Rosario. Pileta, cancha de tenis, gimnasio. Vigilancia las 24 hs.";

            String descripcionLoftBox = "Loft de grandes espacios abiertos y alturas múltiples. Gran espacialidad con todas las comodidades, vista al bosque, y cercanía a playa Punta de Lobos.\n" +
                    "El alojamiento\n" +
                    "El Loft está en Camino Antiguo a Punta de Lobos, en un entorno de bosques, y a 2 km. de la playa\n" +
                    "Acceso de los huéspedes\n" +
                    "La casa cuenta con un quincho full equipado.";

            Alojamiento d0 = new Departamento("Céntrico studio", descripcionCentricoStudio, 3, 10440.0, true, 7540.0, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d0);
            Alojamiento d1 = new Departamento("Departamento Santa Fe Boulevard", descripcionDepto1, 2, 1000.00, false, 200.00, 1, ubicacion1, false);

            gestorAlojamiento.agregarAlojamiento(d1);
            Alojamiento d2 = new Departamento("Calidad Premium", descripcionCalidadPremium, 2, 13920.0, true, 11890.0, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d2);
            Alojamiento d3 = new Departamento("LoftBox", descripcionLoftBox, 7, 43510.0, true, 11970.0, 3, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d3);
            Alojamiento d4 = new Departamento("Departamento4", "Departamento4 - " + loremIpsum, 4, 400.00, true, 200.00, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d4);
            Alojamiento d5 = new Departamento("Departamento5", "Departamento5 - " + loremIpsum, 5, 500.00, true, 200.00, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d5);
            Alojamiento d6 = new Departamento("Departamento6", "Departamento6 - " + loremIpsum, 6, 600.00, true, 200.00, 2, ubicacion, false);
            gestorAlojamiento.agregarAlojamiento(d6);

            Alojamiento h1 = new Habitacion("Habitacion1", "Habitacion1 - " + loremIpsum, 1, 100.0, 1, 0, true, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h1);
            Alojamiento h2 = new Habitacion("Habitacion2", "Habitacion2 - " + loremIpsum, 4, 200.0, 0, 2, false, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h2);
            Alojamiento h3 = new Habitacion("Habitacion3", "Habitacion3 - " + loremIpsum, 3, 300.0, 3, 3, true, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h3);
            Alojamiento h4 = new Habitacion("Habitacion4", "Habitacion4 - " + loremIpsum, 4, 400.0, 4, 4, false, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h4);
            Alojamiento h5 = new Habitacion("Habitacion5", "Habitacion5 - " + loremIpsum, 5, 500.0, 5, 5, true, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h5);
            Alojamiento h6 = new Habitacion("Habitacion6", "Habitacion6 - " + loremIpsum, 6, 600.0, 6, 6, false, hotel, false);
            gestorAlojamiento.agregarAlojamiento(h6);
        }
    }
}