package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.transition.MaterialElevationScale;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.recyclerView.AlojamientoRecyclerAdapter;
import com.mdgz.dam.labdam2022.viewModels.MainActivityViewModel;
import com.mdgz.dam.labdam2022.viewModels.ResultadoBusquedaViewModel;
import com.mdgz.dam.labdam2022.viewModels.factories.MainActivityViewModelFactory;
import com.mdgz.dam.labdam2022.viewModels.factories.ResultadoBusquedaViewModelFactory;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class ResultadoBusquedaFragment extends Fragment implements AlojamientoRecyclerAdapter.OnNoteListener{
    //private GestorAlojamiento gestorAlojamiento;

    private FragmentResultadoBusquedaBinding binding;

    private ResultadoBusquedaViewModel viewModel;
    private MainActivityViewModel viewModelMainActivity;

    private RecyclerView recyclerView;
    private AlojamientoRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // Nombre del archivo
    final private String FILENAME = "logs";
    // Contexto
    private Context ctx;
    // IDLog
    final private Integer IDLog = 6;
    // Cantidad de alojamientos encontrados
    private Integer cantidadAlojamientosEncontrados;
    // Lista de criterios de busqueda
    private List<String> ListaCriteriosDeBusqueda = new ArrayList();

    private MaterialElevationScale transicionElevationScale_exit;

    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //gestorAlojamiento = GestorAlojamiento.getInstance(getContext());
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

        // Se pospone la transicion de entrada para que funcione la transicion al volver
        postponeEnterTransition();
        OneShotPreDrawListener.add(view, this::startPostponedEnterTransition);


        // Se busca el viewModel correspondiente a la actividad
        viewModelMainActivity = new ViewModelProvider(requireActivity(), new MainActivityViewModelFactory()).get(
                MainActivityViewModel.class);


        // Se busca el viewModel correspondiente al fragmento
        viewModel = new ViewModelProvider(this, new ResultadoBusquedaViewModelFactory(getContext())).get(
                ResultadoBusquedaViewModel.class);


        // Se setea el recycler view
        recyclerView = binding.recyclerAlojamiento;

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AlojamientoRecyclerAdapter(new ArrayList<>(), this);
        adapter.setOnFavoriteChangedListener((posicion, nuevoEstado) -> {
            viewModel.cambiarFavorito(posicion, nuevoEstado);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setClickable(true);


        viewModelMainActivity.usuario.observe(getViewLifecycleOwner(), u -> {
            viewModel.setearUsuario(u.getId());
        });

        viewModel.loading.observe(getViewLifecycleOwner(), loading -> {
            if (loading) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.labelResultadoBusqueda.setVisibility(View.INVISIBLE);
                binding.recyclerAlojamiento.setVisibility(View.INVISIBLE);
            }
            else {
                binding.progressBar.setVisibility(View.GONE);
                binding.labelResultadoBusqueda.setVisibility(View.VISIBLE);
                binding.recyclerAlojamiento.setVisibility(View.VISIBLE);
            }
        });

        viewModel.alojamientoCollection.observe(getViewLifecycleOwner(), pairAlojamientos -> {
            List<Alojamiento> alojamientoList = pairAlojamientos.first;
            Integer posicion = pairAlojamientos.second;

            if (adapter.getItemCount() == 0 || posicion == null) {
                adapter.setData(alojamientoList);
                binding.labelResultadoBusqueda.setText(alojamientoList.size() + " resultados encontrados");
            }
            else {
                adapter.favoritoCambiado(posicion, alojamientoList.get(posicion).getEsFavorito());
            }
        });

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

            viewModelMainActivity.setearAlojamientoSeleccionado(adapter.getItem(posicion));

            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(selectedViewHolder.card, selectedViewHolder.card.getTransitionName())
                    .build();

            setExitTransition(transicionElevationScale_exit);

            NavHostFragment.findNavController(ResultadoBusquedaFragment.this)
                    .navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment, bundle, null, extras); //TODO: Faltaría la animación
        }
    }

}

