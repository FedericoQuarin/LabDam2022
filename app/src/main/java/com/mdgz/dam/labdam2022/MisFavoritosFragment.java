package com.mdgz.dam.labdam2022;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.MaterialElevationScale;
import com.mdgz.dam.labdam2022.databinding.FragmentMisFavoritosBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentMisReservasBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.recyclerView.AlojamientoRecyclerAdapter;
import com.mdgz.dam.labdam2022.recyclerView.ReservaRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MisFavoritosFragment extends Fragment implements AlojamientoRecyclerAdapter.OnNoteListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private FragmentMisFavoritosBinding binding;

    private String mParam1;
    private String mParam2;

    private MaterialElevationScale transicionElevationScale_exit;

    public MisFavoritosFragment() {
        // Required empty public constructor
    }

    public static MisFavoritosFragment newInstance(String param1, String param2) {
        MisFavoritosFragment fragment = new MisFavoritosFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMisFavoritosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Se pospone la transicion de entrada para que funcione la transicion al volver
        postponeEnterTransition();
        OneShotPreDrawListener.add(view, this::startPostponedEnterTransition);

        this.recyclerView = binding.recyclerMisFavoritos;
        this.layoutManager = new LinearLayoutManager(view.getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        List<Alojamiento> listaFantasma = new ArrayList<>();

        this.adapter = new AlojamientoRecyclerAdapter(metodoTemporalParaQueCompile(), this);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setClickable(true);

        // Borra cualquier transicion que se haya colocado previamente
        setExitTransition(null);

        // Se busca el tiempo que deben durar las transiciones a detalle
        int TIEMPO_TRANSICION_A_DETALLE = getResources().getInteger(R.integer.transition_time_container_transform);

        // Se crea la transicion de salida
        transicionElevationScale_exit = new MaterialElevationScale(false);
        transicionElevationScale_exit.setDuration(TIEMPO_TRANSICION_A_DETALLE);
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

            setExitTransition(transicionElevationScale_exit);

            NavHostFragment.findNavController(MisFavoritosFragment.this)
                    .navigate(R.id.action_misFavoritosFragment_to_detalleAlojamientoFragment, bundle, null, extras); //TODO: Faltaría la animación
        }
    }

    private List<Alojamiento> metodoTemporalParaQueCompile(){

        List<Alojamiento> lista = new ArrayList<>();

        Ciudad ciudad = new Ciudad("Parana", "PNA");
        Ubicacion ubicacion = new Ubicacion(50.0, 30.0, "Calle principal", "1", ciudad);

        String descripcion = "El Departamento Santa Fe Boulevard ofrece alojamiento con wifi gratis y vistas al jardín en Santa Fe, a solo 1,7 km de la Universidad Nacional del Litoral y a 700 metros de la Plazoleta. El alojamiento se encuentra a 27 km de la terminal de micros de Paraná y a 28 km de la plaza de Mayo.\n" +
                "Departamento con aire acondicionado, 2 dormitorios, TV de pantalla plana y cocina.\n" +
                "Cerca del departamento hay varios lugares de interés, como la plaza de las Banderas, Emilio Zola y la plaza Francia. El aeropuerto más cercano es el de Sauce Viejo, ubicado a 21 km del Departamento Santa Fe Boulevard.";


        Alojamiento d0 = new Departamento("Céntrico studio", descripcion, 3, 10440.0, true, 7540.0, 2, ubicacion, false);
        lista.add(d0);

        return lista;
    }
}

