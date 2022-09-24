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
import com.mdgz.dam.labdam2022.model.Departamento;
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

        Alojamiento a1 = new Departamento(1, "Departamento1", "", 1, 100.00, true, 200.00, 2, null);
        Alojamiento a2 = new Departamento(2, "Departamento2", "", 2, 200.00, true, 200.00, 2, null);
        Alojamiento a3 = new Departamento(3, "Departamento3", "", 3, 300.00, true, 200.00, 2, null);
        Alojamiento a4 = new Departamento(4, "Departamento4", "", 4, 400.00, true, 200.00, 2, null);
        Alojamiento a5 = new Departamento(5, "Departamento5", "", 5, 500.00, true, 200.00, 2, null);
        Alojamiento a6 = new Departamento(6, "Departamento6", "", 6, 600.00, true, 200.00, 2, null);

        gA.agregarAlojamiento(a1);
        gA.agregarAlojamiento(a2);
        gA.agregarAlojamiento(a3);
        gA.agregarAlojamiento(a4);
        gA.agregarAlojamiento(a5);
        gA.agregarAlojamiento(a6);
    }
}